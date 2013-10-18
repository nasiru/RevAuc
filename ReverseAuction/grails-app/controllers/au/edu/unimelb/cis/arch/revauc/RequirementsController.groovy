package au.edu.unimelb.cis.arch.revauc

import org.springframework.dao.DataIntegrityViolationException

class RequirementsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [requirementsInstanceList: Requirements.list(params), requirementsInstanceTotal: Requirements.count()]
    }

    def create() {
        [requirementsInstance: new Requirements(params)]
    }

    def save() {
        def requirementsInstance = new Requirements(params)
        if (!requirementsInstance.save(flush: true)) {
            render(view: "create", model: [requirementsInstance: requirementsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'requirements.label', default: 'Requirements'), requirementsInstance.id])
        redirect(action: "show", id: requirementsInstance.id)
    }

    def show(Long id) {
        def requirementsInstance = Requirements.get(id)
        if (!requirementsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'requirements.label', default: 'Requirements'), id])
            redirect(action: "list")
            return
        }

        [requirementsInstance: requirementsInstance]
    }

    def edit(Long id) {
        def requirementsInstance = Requirements.get(id)
        if (!requirementsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'requirements.label', default: 'Requirements'), id])
            redirect(action: "list")
            return
        }

        [requirementsInstance: requirementsInstance]
    }

    def update(Long id, Long version) {
        def requirementsInstance = Requirements.get(id)
        if (!requirementsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'requirements.label', default: 'Requirements'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (requirementsInstance.version > version) {
                requirementsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'requirements.label', default: 'Requirements')] as Object[],
                          "Another user has updated this Requirements while you were editing")
                render(view: "edit", model: [requirementsInstance: requirementsInstance])
                return
            }
        }

        requirementsInstance.properties = params

        if (!requirementsInstance.save(flush: true)) {
            render(view: "edit", model: [requirementsInstance: requirementsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'requirements.label', default: 'Requirements'), requirementsInstance.id])
        redirect(action: "show", id: requirementsInstance.id)
    }

    def delete(Long id) {
        def requirementsInstance = Requirements.get(id)
        if (!requirementsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'requirements.label', default: 'Requirements'), id])
            redirect(action: "list")
            return
        }

        try {
            requirementsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'requirements.label', default: 'Requirements'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'requirements.label', default: 'Requirements'), id])
            redirect(action: "show", id: id)
        }
    }
}
