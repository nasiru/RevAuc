package au.edu.unimelb.cis.arch.revauc

import org.springframework.dao.DataIntegrityViolationException

class AuctionController {

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
			it.status.category = Status.get(2).category
			it.save(flush: true)
		}

		[auctionInstanceList: Auction.list(params), auctionInstanceTotal: Auction.count()]
	}

	def create() {
		[auctionInstance: new Auction(params)]
	}

	def save() {

		def auctionInstance = new Auction(params)

		auctionInstance.status = Status.get(1)
		auctionInstance.datePosted = new Date()

		def bidsInstance = new Bids(params)
		bidsInstance.bidDate = new Date()

		// grab all requirements using grep
		def reqKeys = params.keySet().grep(~/requirementsList\[.+\]/)
		def reqMap = params.subMap(reqKeys) as TreeMap

		// clear all to refresh
		auctionInstance.requirements.clear()

		reqMap.each() {
			//println it.value
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

			render(view: "create", model: [auctionInstance: auctionInstance, bidsInstance: bidsInstance])
			return
		}

		if (!auctionInstance.save(flush: true)) {
			render(view: "create", model: [auctionInstance: auctionInstance, bidsInstance: bidsInstance])
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

		[auctionInstance: auctionInstance, minBid: auctionInstance.bids.price.min(), bidList: auctionInstance.bids.toList()]
	}

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

		[auctionInstance: auctionInstance]
	}

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
				render(view: "edit", model: [auctionInstance: auctionInstance])
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
			//println it.value
			auctionInstance.addToRequirements(new Requirements(it.value))
		}

		// find the requirements that are marked for deletion
		def _toBeDeleted = auctionInstance.getRequirementsList().findAll {(it?.deleted || (it == null))}


		// if there are requirements to be deleted remove them all
		if (_toBeDeleted) {
			auctionInstance.requirements.removeAll(_toBeDeleted)
		}



		if (!auctionInstance.save(flush: true)) {
			render(view: "edit", model: [auctionInstance: auctionInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'auction.label', default: 'Auction'),
			auctionInstance.id
		])
		redirect(action: "show", id: auctionInstance.id)
	}

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

	// reserved for future pagination in show.gsp
	//.size() < 10 ? auctionInstance.bids : auctionInstance.bids.toList().subList(0,10)
}
