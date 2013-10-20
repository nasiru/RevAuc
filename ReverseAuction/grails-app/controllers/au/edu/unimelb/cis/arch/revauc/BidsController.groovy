package au.edu.unimelb.cis.arch.revauc

import grails.plugin.springsecurity.annotation.*

import org.springframework.dao.DataIntegrityViolationException
class BidsController {
	def springSecurityService

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[bidsInstanceList: Bids.list(params), bidsInstanceTotal: Bids.count()]
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def create() {

		session["auctionID"] = params.get("auction.id")

		[bidsInstance: new Bids(params)]
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def save() {
		def bidsInstance = new Bids(params)
		def auction = Auction.get(params.auctionId)

		//bidsInstance.auction = Auction.get(params.auctionId)
		bidsInstance.bidDate = new Date()
		bidsInstance.user = currentUser()

		def minBid = auction.bids.price.min()

		if(bidsInstance.hasErrors() || params.price.isEmpty()) {
			flash.message = message(code: 'bids.invalid.message', args: [])

			redirect(controller: "auction", action: "show", id: auction.id)
			return
		}

		// minimum bid check
		if (minBid && minBid - 0.01 <= bidsInstance.price) {
			flash.message = message(code: 'bids.highbid.message', args: [])

			redirect(controller: "auction", action: "show", id: auction.id)
			return
		}

		// save new leader and min bid
		auction.minBid = bidsInstance.price
		auction.leader = bidsInstance.user.username

		auction.addToBids(bidsInstance)

		// optimistic locking check
		//if (!bidsInstance.save(flush: true)) {
		if (!auction.save(flush: true)) {
			flash.message = message(code: 'bids.negative.message', args: [])
			redirect(controller: "auction", action: "show", id: auction.id)
			return
		}

		flash.message = message(code: 'bids.created.message', args: [
			message(code: 'bids.label', default: 'Bids'),
			bidsInstance.id
		])
		redirect(controller: "auction", action: "show", id: bidsInstance.auction.id)
	}

	def show(Long id) {
		def bidsInstance = Bids.get(id)
		if (!bidsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'bids.label', default: 'Bids'),
				id
			])
			redirect(action: "list")
			return
		}

		[bidsInstance: bidsInstance]
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def edit(Long id) {
		def bidsInstance = Bids.get(id)
		if (!bidsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'bids.label', default: 'Bids'),
				id
			])
			redirect(action: "list")
			return
		}

		[bidsInstance: bidsInstance]
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def update(Long id, Long version) {
		def bidsInstance = Bids.get(id)
		if (!bidsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'bids.label', default: 'Bids'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (bidsInstance.version > version) {
				bidsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'bids.label', default: 'Bids')] as Object[],
						"Another user has updated this Bid while you were editing")
				render(view: "edit", model: [bidsInstance: bidsInstance])
				return
			}
		}

		bidsInstance.properties = params

		if (!bidsInstance.save(flush: true)) {
			render(view: "edit", model: [bidsInstance: bidsInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'bids.label', default: 'Bids'),
			bidsInstance.id
		])
		redirect(action: "show", id: bidsInstance.id)
	}

	@Secured([
		'ROLE_USER',
		'ROLE_ADMIN',
		'IS_AUTHENTICATED_FULLY'
	])
	def delete(Long id) {
		def bidsInstance = Bids.get(id)
		if (!bidsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'bids.label', default: 'Bids'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			bidsInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'bids.label', default: 'Bids'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'bids.label', default: 'Bids'),
				id
			])
			redirect(action: "show", id: id)
		}
	}

	private currentUser() {
		User.get(springSecurityService.principal.id)
	}
}
