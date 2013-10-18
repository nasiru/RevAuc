package au.edu.unimelb.cis.arch.revauc



import org.junit.*
import grails.test.mixin.*

@TestFor(AuctionController)
@Mock(Auction)
class AuctionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/auction/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.auctionInstanceList.size() == 0
        assert model.auctionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.auctionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.auctionInstance != null
        assert view == '/auction/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/auction/show/1'
        assert controller.flash.message != null
        assert Auction.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/auction/list'

        populateValidParams(params)
        def auction = new Auction(params)

        assert auction.save() != null

        params.id = auction.id

        def model = controller.show()

        assert model.auctionInstance == auction
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/auction/list'

        populateValidParams(params)
        def auction = new Auction(params)

        assert auction.save() != null

        params.id = auction.id

        def model = controller.edit()

        assert model.auctionInstance == auction
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/auction/list'

        response.reset()

        populateValidParams(params)
        def auction = new Auction(params)

        assert auction.save() != null

        // test invalid parameters in update
        params.id = auction.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/auction/edit"
        assert model.auctionInstance != null

        auction.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/auction/show/$auction.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        auction.clearErrors()

        populateValidParams(params)
        params.id = auction.id
        params.version = -1
        controller.update()

        assert view == "/auction/edit"
        assert model.auctionInstance != null
        assert model.auctionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/auction/list'

        response.reset()

        populateValidParams(params)
        def auction = new Auction(params)

        assert auction.save() != null
        assert Auction.count() == 1

        params.id = auction.id

        controller.delete()

        assert Auction.count() == 0
        assert Auction.get(auction.id) == null
        assert response.redirectedUrl == '/auction/list'
    }
}
