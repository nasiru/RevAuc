package au.edu.unimelb.cis.arch.revauc



import org.junit.*
import grails.test.mixin.*

@TestFor(ItemCategoryController)
@Mock(ItemCategory)
class ItemCategoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/itemCategory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.itemCategoryInstanceList.size() == 0
        assert model.itemCategoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.itemCategoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.itemCategoryInstance != null
        assert view == '/itemCategory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/itemCategory/show/1'
        assert controller.flash.message != null
        assert ItemCategory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/itemCategory/list'

        populateValidParams(params)
        def itemCategory = new ItemCategory(params)

        assert itemCategory.save() != null

        params.id = itemCategory.id

        def model = controller.show()

        assert model.itemCategoryInstance == itemCategory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/itemCategory/list'

        populateValidParams(params)
        def itemCategory = new ItemCategory(params)

        assert itemCategory.save() != null

        params.id = itemCategory.id

        def model = controller.edit()

        assert model.itemCategoryInstance == itemCategory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/itemCategory/list'

        response.reset()

        populateValidParams(params)
        def itemCategory = new ItemCategory(params)

        assert itemCategory.save() != null

        // test invalid parameters in update
        params.id = itemCategory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/itemCategory/edit"
        assert model.itemCategoryInstance != null

        itemCategory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/itemCategory/show/$itemCategory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        itemCategory.clearErrors()

        populateValidParams(params)
        params.id = itemCategory.id
        params.version = -1
        controller.update()

        assert view == "/itemCategory/edit"
        assert model.itemCategoryInstance != null
        assert model.itemCategoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/itemCategory/list'

        response.reset()

        populateValidParams(params)
        def itemCategory = new ItemCategory(params)

        assert itemCategory.save() != null
        assert ItemCategory.count() == 1

        params.id = itemCategory.id

        controller.delete()

        assert ItemCategory.count() == 0
        assert ItemCategory.get(itemCategory.id) == null
        assert response.redirectedUrl == '/itemCategory/list'
    }
}
