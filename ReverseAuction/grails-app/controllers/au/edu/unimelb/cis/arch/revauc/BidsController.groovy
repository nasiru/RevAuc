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
		def user = session["auctionID"]
		session["auctionID"] = params.get("auction.id")

		[bidsInstance: new Bids(params)]
	}

	def save() {
		def bidsInstance = new Bids(params)

		bidsInstance.auction = Auction.get(session["auctionID"])
		bidsInstance.bidDate = new Date()


		if (!bidsInstance.save(flush: true)) {
			render(view: "create", model: [bidsInstance: bidsInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'bids.label', default: 'Bids'),
			bidsInstance.id
		])
		redirect(action: "show", id: bidsInstance.id)
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
						"Another user has updated this Bids while you were editing")
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
