package testConnect

import org.springframework.dao.DataIntegrityViolationException

class CountrylanguageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [countrylanguageInstanceList: Countrylanguage.list(params), countrylanguageInstanceTotal: Countrylanguage.count()]
    }

    def create() {
        [countrylanguageInstance: new Countrylanguage(params)]
    }

    def save() {
        def countrylanguageInstance = new Countrylanguage(params)
        if (!countrylanguageInstance.save(flush: true)) {
            render(view: "create", model: [countrylanguageInstance: countrylanguageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'countrylanguage.label', default: 'Countrylanguage'), countrylanguageInstance.id])
        redirect(action: "show", id: countrylanguageInstance.id)
    }

    def show(Long id) {
        def countrylanguageInstance = Countrylanguage.get(id)
        if (!countrylanguageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'countrylanguage.label', default: 'Countrylanguage'), id])
            redirect(action: "list")
            return
        }

        [countrylanguageInstance: countrylanguageInstance]
    }

    def edit(Long id) {
        def countrylanguageInstance = Countrylanguage.get(id)
        if (!countrylanguageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'countrylanguage.label', default: 'Countrylanguage'), id])
            redirect(action: "list")
            return
        }

        [countrylanguageInstance: countrylanguageInstance]
    }

    def update(Long id, Long version) {
        def countrylanguageInstance = Countrylanguage.get(id)
        if (!countrylanguageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'countrylanguage.label', default: 'Countrylanguage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (countrylanguageInstance.version > version) {
                countrylanguageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'countrylanguage.label', default: 'Countrylanguage')] as Object[],
                          "Another user has updated this Countrylanguage while you were editing")
                render(view: "edit", model: [countrylanguageInstance: countrylanguageInstance])
                return
            }
        }

        countrylanguageInstance.properties = params

        if (!countrylanguageInstance.save(flush: true)) {
            render(view: "edit", model: [countrylanguageInstance: countrylanguageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'countrylanguage.label', default: 'Countrylanguage'), countrylanguageInstance.id])
        redirect(action: "show", id: countrylanguageInstance.id)
    }

    def delete(Long id) {
        def countrylanguageInstance = Countrylanguage.get(id)
        if (!countrylanguageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'countrylanguage.label', default: 'Countrylanguage'), id])
            redirect(action: "list")
            return
        }

        try {
            countrylanguageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'countrylanguage.label', default: 'Countrylanguage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'countrylanguage.label', default: 'Countrylanguage'), id])
            redirect(action: "show", id: id)
        }
    }
}
