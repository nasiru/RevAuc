package au.edu.unimelb.cis.arch.revauc

import grails.plugin.springsecurity.annotation.*

import org.springframework.dao.DataIntegrityViolationException
class AuctionController {
	def springSecurityService
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {

		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)

		// expire all elapsed auctions
		def today = new Date()
		def expiredList = Auction.findAll { dateEnding < today && status.category == "Active"}

		expiredList.each() {
			it.status = Status.findByCategory("Expired")
			it.save(flush: true)
		}

		[auctionInstanceList: Auction.list(params), auctionInstanceTotal: Auction.count(), userid: currentUser() != null ? currentUser().id : 1]
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def create() {
		[auctionInstance: new Auction(params), userid: currentUser() != null ? currentUser().id : 1]
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def save() {

		def auctionInstance = new Auction(params)
		auctionInstance.status = Status.get(1)
		auctionInstance.datePosted = new Date()
		auctionInstance.user = currentUser()

		auctionInstance.leader = "---"

		def bidsInstance = new Bids(params)
		bidsInstance.bidDate = new Date()
		bidsInstance.user = currentUser()

		// grab all requirements using grep
		def reqKeys = params.keySet().grep(~/requirementsList\[.+\]/)
		def reqMap = params.subMap(reqKeys) as TreeMap

		// clear all to refresh
		auctionInstance.requirements.clear()

		reqMap.each() {
			auctionInstance.addToRequirements(new Requirements(it.value))
		}

		// find the requirements that are marked for deletion
		def _toBeDeleted = auctionInstance.getRequirementsList().findAll {(it?.deleted || (it == null))}


		// if there are requirements to be deleted remove them all
		if (_toBeDeleted) {
			auctionInstance.requirements.removeAll(_toBeDeleted)
		}

		auctionInstance.addToBids(bidsInstance)

		if(bidsInstance.hasErrors() || params.price.isEmpty()) {
			flash.message = message(code: 'bids.invalid.message', args: [])

			render(view: "create", model: [auctionInstance: auctionInstance, bidsInstance: bidsInstance, userid: currentUser() != null ? currentUser().id : 1])
			return
		}

		// save as initial min bid
		auctionInstance.minBid = bidsInstance.price

		def userInstance = User.get(currentUser().id)

		userInstance.addToAuctions(auctionInstance)

		if (!userInstance.save(flush: true)) {
			render(view: "create", model: [auctionInstance: auctionInstance, bidsInstance: bidsInstance, userid: currentUser() != null ? currentUser().id : 1])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'auction.label', default: 'Auction'),
			auctionInstance.id
		])
		redirect(action: "show", id: auctionInstance.id)
	}

	def show(Long id) {
		def auctionInstance = Auction.get(id)

		if (!auctionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'auction.label', default: 'Auction'),
				id
			])
			redirect(action: "list")
			return
		}

		[auctionInstance: auctionInstance, minBid: auctionInstance.bids.price.min(), bidList: auctionInstance.bids.toList(), userid: currentUser() != null ? currentUser().id : 1]
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def edit(Long id) {
		def auctionInstance = Auction.get(id)
		if (!auctionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'auction.label', default: 'Auction'),
				id
			])
			redirect(action: "list")
			return
		}

		[auctionInstance: auctionInstance, userid: currentUser() != null ? currentUser().id : 1]
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def update(Long id, Long version) {
		def auctionInstance = Auction.get(id)
		if (!auctionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'auction.label', default: 'Auction'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (auctionInstance.version > version) {
				auctionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'auction.label', default: 'Auction')] as Object[],
						"Another user has updated this Auction while you were editing")
				render(view: "edit", model: [auctionInstance: auctionInstance, userid: currentUser() != null ? currentUser().id : 1])
				return
			}
		}

		auctionInstance.properties = params

		// grab all requirements using grep
		def reqKeys = params.keySet().grep(~/requirementsList\[.+\]/)
		def reqMap = params.subMap(reqKeys) as TreeMap

		// clear all to refresh
		auctionInstance.requirements.clear()

		reqMap.each() {
			auctionInstance.addToRequirements(new Requirements(it.value))
		}

		// find the requirements that are marked for deletion
		def _toBeDeleted = auctionInstance.getRequirementsList().findAll {(it?.deleted || (it == null))}


		// if there are requirements to be deleted remove them all
		if (_toBeDeleted) {
			auctionInstance.requirements.removeAll(_toBeDeleted)
		}



		if (!auctionInstance.save(flush: true)) {
			render(view: "edit", model: [auctionInstance: auctionInstance, userid: currentUser() != null ? currentUser().id : 1, id: auctionInstance.id])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'auction.label', default: 'Auction'),
			auctionInstance.id
		])
		redirect(action: "show", id: auctionInstance.id)
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def delete(Long id) {
		def auctionInstance = Auction.get(id)
		if (!auctionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'auction.label', default: 'Auction'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			auctionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'auction.label', default: 'Auction'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'auction.label', default: 'Auction'),
				id
			])
			redirect(action: "show", id: id)
		}
	}

	def close() {
		def auctionInstance = Auction.get(params.auctionId)

		auctionInstance.status = Status.findByCategory("Completed")

		auctionInstance.save()

		redirect(action: "show", id: params.auctionId)
	}

	private currentUser() {
		if (springSecurityService.isLoggedIn())
			User.get(springSecurityService.principal.id)
		else null
	}

	// reserved for future pagination in show.gsp
	//.size() < 10 ? auctionInstance.bids : auctionInstance.bids.toList().subList(0,10)
}
