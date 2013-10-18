package au.edu.unimelb.cis.arch.revauc

import org.springframework.dao.DataIntegrityViolationException

class AuctionHistoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [auctionHistoryInstanceList: AuctionHistory.list(params), auctionHistoryInstanceTotal: AuctionHistory.count()]
    }

    def create() {
        [auctionHistoryInstance: new AuctionHistory(params)]
    }

    def save() {
        def auctionHistoryInstance = new AuctionHistory(params)
        if (!auctionHistoryInstance.save(flush: true)) {
            render(view: "create", model: [auctionHistoryInstance: auctionHistoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory'), auctionHistoryInstance.id])
        redirect(action: "show", id: auctionHistoryInstance.id)
    }

    def show(Long id) {
        def auctionHistoryInstance = AuctionHistory.get(id)
        if (!auctionHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory'), id])
            redirect(action: "list")
            return
        }

        [auctionHistoryInstance: auctionHistoryInstance]
    }

    def edit(Long id) {
        def auctionHistoryInstance = AuctionHistory.get(id)
        if (!auctionHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory'), id])
            redirect(action: "list")
            return
        }

        [auctionHistoryInstance: auctionHistoryInstance]
    }

    def update(Long id, Long version) {
        def auctionHistoryInstance = AuctionHistory.get(id)
        if (!auctionHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (auctionHistoryInstance.version > version) {
                auctionHistoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'auctionHistory.label', default: 'AuctionHistory')] as Object[],
                          "Another user has updated this AuctionHistory while you were editing")
                render(view: "edit", model: [auctionHistoryInstance: auctionHistoryInstance])
                return
            }
        }

        auctionHistoryInstance.properties = params

        if (!auctionHistoryInstance.save(flush: true)) {
            render(view: "edit", model: [auctionHistoryInstance: auctionHistoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory'), auctionHistoryInstance.id])
        redirect(action: "show", id: auctionHistoryInstance.id)
    }

    def delete(Long id) {
        def auctionHistoryInstance = AuctionHistory.get(id)
        if (!auctionHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory'), id])
            redirect(action: "list")
            return
        }

        try {
            auctionHistoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory'), id])
            redirect(action: "show", id: id)
        }
    }
}
