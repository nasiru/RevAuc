package au.edu.unimelb.cis.arch.revauc



import org.junit.*
import grails.test.mixin.*

@TestFor(BidsController)
@Mock(Bids)
class BidsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/bids/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bidsInstanceList.size() == 0
        assert model.bidsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.bidsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bidsInstance != null
        assert view == '/bids/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bids/show/1'
        assert controller.flash.message != null
        assert Bids.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bids/list'

        populateValidParams(params)
        def bids = new Bids(params)

        assert bids.save() != null

        params.id = bids.id

        def model = controller.show()

        assert model.bidsInstance == bids
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bids/list'

        populateValidParams(params)
        def bids = new Bids(params)

        assert bids.save() != null

        params.id = bids.id

        def model = controller.edit()

        assert model.bidsInstance == bids
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bids/list'

        response.reset()

        populateValidParams(params)
        def bids = new Bids(params)

        assert bids.save() != null

        // test invalid parameters in update
        params.id = bids.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bids/edit"
        assert model.bidsInstance != null

        bids.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bids/show/$bids.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bids.clearErrors()

        populateValidParams(params)
        params.id = bids.id
        params.version = -1
        controller.update()

        assert view == "/bids/edit"
        assert model.bidsInstance != null
        assert model.bidsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bids/list'

        response.reset()

        populateValidParams(params)
        def bids = new Bids(params)

        assert bids.save() != null
        assert Bids.count() == 1

        params.id = bids.id

        controller.delete()

        assert Bids.count() == 0
        assert Bids.get(bids.id) == null
        assert response.redirectedUrl == '/bids/list'
    }
}
