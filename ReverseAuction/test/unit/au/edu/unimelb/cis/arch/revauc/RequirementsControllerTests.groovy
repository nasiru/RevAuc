package au.edu.unimelb.cis.arch.revauc



import org.junit.*
import grails.test.mixin.*

@TestFor(RequirementsController)
@Mock(Requirements)
class RequirementsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/requirements/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.requirementsInstanceList.size() == 0
        assert model.requirementsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.requirementsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.requirementsInstance != null
        assert view == '/requirements/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/requirements/show/1'
        assert controller.flash.message != null
        assert Requirements.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/requirements/list'

        populateValidParams(params)
        def requirements = new Requirements(params)

        assert requirements.save() != null

        params.id = requirements.id

        def model = controller.show()

        assert model.requirementsInstance == requirements
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/requirements/list'

        populateValidParams(params)
        def requirements = new Requirements(params)

        assert requirements.save() != null

        params.id = requirements.id

        def model = controller.edit()

        assert model.requirementsInstance == requirements
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/requirements/list'

        response.reset()

        populateValidParams(params)
        def requirements = new Requirements(params)

        assert requirements.save() != null

        // test invalid parameters in update
        params.id = requirements.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/requirements/edit"
        assert model.requirementsInstance != null

        requirements.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/requirements/show/$requirements.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        requirements.clearErrors()

        populateValidParams(params)
        params.id = requirements.id
        params.version = -1
        controller.update()

        assert view == "/requirements/edit"
        assert model.requirementsInstance != null
        assert model.requirementsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/requirements/list'

        response.reset()

        populateValidParams(params)
        def requirements = new Requirements(params)

        assert requirements.save() != null
        assert Requirements.count() == 1

        params.id = requirements.id

        controller.delete()

        assert Requirements.count() == 0
        assert Requirements.get(requirements.id) == null
        assert response.redirectedUrl == '/requirements/list'
    }
}
