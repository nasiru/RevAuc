package au.edu.unimelb.cis.arch.revauc

import org.springframework.dao.DataIntegrityViolationException

class AuctionController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[auctionInstanceList: Auction.list(params), auctionInstanceTotal: Auction.count()]
	}

	def create() {
		[auctionInstance: new Auction(params)]
	}

	def save() {

		def auctionInstance = new Auction(params)

		auctionInstance.status = Status.get(1)
		auctionInstance.datePosted = new Date()

		if (!auctionInstance.save(flush: true)) {
			render(view: "create", model: [auctionInstance: auctionInstance])
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

		[auctionInstance: auctionInstance]
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
}
