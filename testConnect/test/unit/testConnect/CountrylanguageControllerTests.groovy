package testConnect



import org.junit.*
import grails.test.mixin.*

@TestFor(CountrylanguageController)
@Mock(Countrylanguage)
class CountrylanguageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/countrylanguage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.countrylanguageInstanceList.size() == 0
        assert model.countrylanguageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.countrylanguageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.countrylanguageInstance != null
        assert view == '/countrylanguage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/countrylanguage/show/1'
        assert controller.flash.message != null
        assert Countrylanguage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/countrylanguage/list'

        populateValidParams(params)
        def countrylanguage = new Countrylanguage(params)

        assert countrylanguage.save() != null

        params.id = countrylanguage.id

        def model = controller.show()

        assert model.countrylanguageInstance == countrylanguage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/countrylanguage/list'

        populateValidParams(params)
        def countrylanguage = new Countrylanguage(params)

        assert countrylanguage.save() != null

        params.id = countrylanguage.id

        def model = controller.edit()

        assert model.countrylanguageInstance == countrylanguage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/countrylanguage/list'

        response.reset()

        populateValidParams(params)
        def countrylanguage = new Countrylanguage(params)

        assert countrylanguage.save() != null

        // test invalid parameters in update
        params.id = countrylanguage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/countrylanguage/edit"
        assert model.countrylanguageInstance != null

        countrylanguage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/countrylanguage/show/$countrylanguage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        countrylanguage.clearErrors()

        populateValidParams(params)
        params.id = countrylanguage.id
        params.version = -1
        controller.update()

        assert view == "/countrylanguage/edit"
        assert model.countrylanguageInstance != null
        assert model.countrylanguageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/countrylanguage/list'

        response.reset()

        populateValidParams(params)
        def countrylanguage = new Countrylanguage(params)

        assert countrylanguage.save() != null
        assert Countrylanguage.count() == 1

        params.id = countrylanguage.id

        controller.delete()

        assert Countrylanguage.count() == 0
        assert Countrylanguage.get(countrylanguage.id) == null
        assert response.redirectedUrl == '/countrylanguage/list'
    }
}
