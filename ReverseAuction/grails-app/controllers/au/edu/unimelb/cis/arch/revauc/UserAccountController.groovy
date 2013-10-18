package au.edu.unimelb.cis.arch.revauc

import org.springframework.dao.DataIntegrityViolationException

class UserAccountController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userAccountInstanceList: UserAccount.list(params), userAccountInstanceTotal: UserAccount.count()]
    }

    def create() {
        [userAccountInstance: new UserAccount(params)]
    }

    def save() {
        def userAccountInstance = new UserAccount(params)
        if (!userAccountInstance.save(flush: true)) {
            render(view: "create", model: [userAccountInstance: userAccountInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userAccount.label', default: 'UserAccount'), userAccountInstance.id])
        redirect(action: "show", id: userAccountInstance.id)
    }

    def show(Long id) {
        def userAccountInstance = UserAccount.get(id)
        if (!userAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userAccount.label', default: 'UserAccount'), id])
            redirect(action: "list")
            return
        }

        [userAccountInstance: userAccountInstance]
    }

    def edit(Long id) {
        def userAccountInstance = UserAccount.get(id)
        if (!userAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userAccount.label', default: 'UserAccount'), id])
            redirect(action: "list")
            return
        }

        [userAccountInstance: userAccountInstance]
    }

    def update(Long id, Long version) {
        def userAccountInstance = UserAccount.get(id)
        if (!userAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userAccount.label', default: 'UserAccount'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userAccountInstance.version > version) {
                userAccountInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userAccount.label', default: 'UserAccount')] as Object[],
                          "Another user has updated this UserAccount while you were editing")
                render(view: "edit", model: [userAccountInstance: userAccountInstance])
                return
            }
        }

        userAccountInstance.properties = params

        if (!userAccountInstance.save(flush: true)) {
            render(view: "edit", model: [userAccountInstance: userAccountInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userAccount.label', default: 'UserAccount'), userAccountInstance.id])
        redirect(action: "show", id: userAccountInstance.id)
    }

    def delete(Long id) {
        def userAccountInstance = UserAccount.get(id)
        if (!userAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userAccount.label', default: 'UserAccount'), id])
            redirect(action: "list")
            return
        }

        try {
            userAccountInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userAccount.label', default: 'UserAccount'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userAccount.label', default: 'UserAccount'), id])
            redirect(action: "show", id: id)
        }
    }
}
