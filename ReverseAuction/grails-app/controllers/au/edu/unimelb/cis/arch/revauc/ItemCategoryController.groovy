package au.edu.unimelb.cis.arch.revauc

import org.springframework.dao.DataIntegrityViolationException

class ItemCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [itemCategoryInstanceList: ItemCategory.list(params), itemCategoryInstanceTotal: ItemCategory.count()]
    }

    def create() {
        [itemCategoryInstance: new ItemCategory(params)]
    }

    def save() {
        def itemCategoryInstance = new ItemCategory(params)
        if (!itemCategoryInstance.save(flush: true)) {
            render(view: "create", model: [itemCategoryInstance: itemCategoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'itemCategory.label', default: 'ItemCategory'), itemCategoryInstance.id])
        redirect(action: "show", id: itemCategoryInstance.id)
    }

    def show(Long id) {
        def itemCategoryInstance = ItemCategory.get(id)
        if (!itemCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemCategory.label', default: 'ItemCategory'), id])
            redirect(action: "list")
            return
        }

        [itemCategoryInstance: itemCategoryInstance]
    }

    def edit(Long id) {
        def itemCategoryInstance = ItemCategory.get(id)
        if (!itemCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemCategory.label', default: 'ItemCategory'), id])
            redirect(action: "list")
            return
        }

        [itemCategoryInstance: itemCategoryInstance]
    }

    def update(Long id, Long version) {
        def itemCategoryInstance = ItemCategory.get(id)
        if (!itemCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemCategory.label', default: 'ItemCategory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (itemCategoryInstance.version > version) {
                itemCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'itemCategory.label', default: 'ItemCategory')] as Object[],
                          "Another user has updated this ItemCategory while you were editing")
                render(view: "edit", model: [itemCategoryInstance: itemCategoryInstance])
                return
            }
        }

        itemCategoryInstance.properties = params

        if (!itemCategoryInstance.save(flush: true)) {
            render(view: "edit", model: [itemCategoryInstance: itemCategoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'itemCategory.label', default: 'ItemCategory'), itemCategoryInstance.id])
        redirect(action: "show", id: itemCategoryInstance.id)
    }

    def delete(Long id) {
        def itemCategoryInstance = ItemCategory.get(id)
        if (!itemCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemCategory.label', default: 'ItemCategory'), id])
            redirect(action: "list")
            return
        }

        try {
            itemCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'itemCategory.label', default: 'ItemCategory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'itemCategory.label', default: 'ItemCategory'), id])
            redirect(action: "show", id: id)
        }
    }
}
