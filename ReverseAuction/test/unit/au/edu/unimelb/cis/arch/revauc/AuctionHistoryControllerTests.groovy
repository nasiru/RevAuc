package au.edu.unimelb.cis.arch.revauc



import org.junit.*
import grails.test.mixin.*

@TestFor(AuctionHistoryController)
@Mock(AuctionHistory)
class AuctionHistoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/auctionHistory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.auctionHistoryInstanceList.size() == 0
        assert model.auctionHistoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.auctionHistoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.auctionHistoryInstance != null
        assert view == '/auctionHistory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/auctionHistory/show/1'
        assert controller.flash.message != null
        assert AuctionHistory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/auctionHistory/list'

        populateValidParams(params)
        def auctionHistory = new AuctionHistory(params)

        assert auctionHistory.save() != null

        params.id = auctionHistory.id

        def model = controller.show()

        assert model.auctionHistoryInstance == auctionHistory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/auctionHistory/list'

        populateValidParams(params)
        def auctionHistory = new AuctionHistory(params)

        assert auctionHistory.save() != null

        params.id = auctionHistory.id

        def model = controller.edit()

        assert model.auctionHistoryInstance == auctionHistory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/auctionHistory/list'

        response.reset()

        populateValidParams(params)
        def auctionHistory = new AuctionHistory(params)

        assert auctionHistory.save() != null

        // test invalid parameters in update
        params.id = auctionHistory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/auctionHistory/edit"
        assert model.auctionHistoryInstance != null

        auctionHistory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/auctionHistory/show/$auctionHistory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        auctionHistory.clearErrors()

        populateValidParams(params)
        params.id = auctionHistory.id
        params.version = -1
        controller.update()

        assert view == "/auctionHistory/edit"
        assert model.auctionHistoryInstance != null
        assert model.auctionHistoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/auctionHistory/list'

        response.reset()

        populateValidParams(params)
        def auctionHistory = new AuctionHistory(params)

        assert auctionHistory.save() != null
        assert AuctionHistory.count() == 1

        params.id = auctionHistory.id

        controller.delete()

        assert AuctionHistory.count() == 0
        assert AuctionHistory.get(auctionHistory.id) == null
        assert response.redirectedUrl == '/auctionHistory/list'
    }
}
