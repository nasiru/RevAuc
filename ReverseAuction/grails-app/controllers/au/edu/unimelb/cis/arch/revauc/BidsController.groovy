package au.edu.unimelb.cis.arch.revauc

import org.springframework.dao.DataIntegrityViolationException

class BidsController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[bidsInstanceList: Bids.list(params), bidsInstanceTotal: Bids.count()]
	}

	def create() {

		session["auctionID"] = params.get("auction.id")

		[bidsInstance: new Bids(params)]
	}

	def save() {
		def bidsInstance = new Bids(params)

		bidsInstance.auction = Auction.get(params.auctionId)
		bidsInstance.bidDate = new Date()

		params.minBid = bidsInstance.auction.bids.price.min()

		if(bidsInstance.hasErrors() || params.price.isEmpty()) {
			flash.message = message(code: 'bids.invalid.message', args: [])

			redirect(controller: "auction", action: "show", id: bidsInstance.auction.id)
			return
		}

		// optimistic locking check and minimum bid check
		if (params.minBid && params.minBid - 0.01 <= new BigDecimal(params.price)) {
			flash.message = message(code: 'bids.highbid.message', args: [])

			redirect(controller: "auction", action: "show", id: bidsInstance.auction.id)
			return
		}

		if (!bidsInstance.save(flush: true)) {
			flash.message = message(code: 'bids.negative.message', args: [])
			redirect(controller: "auction", action: "show", id: bidsInstance.auction.id)
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

}
