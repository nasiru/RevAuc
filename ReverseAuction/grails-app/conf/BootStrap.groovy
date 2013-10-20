import au.edu.unimelb.cis.arch.revauc.Auction
import au.edu.unimelb.cis.arch.revauc.ItemCategory
import au.edu.unimelb.cis.arch.revauc.Status

class BootStrap {

	def init = { servletContext ->
		def date = new Date();

		def statusActive = new Status(category: "Active").save();
		def statusExpired = new Status(category: "Expired").save();
		def statusCompleted = new Status(category: "Completed").save();

		def category1 = new ItemCategory(name: "Food", description: "Things you can eat").save()
		def category2 = new ItemCategory(name: "Electronics", description: "Things that can zap you").save()
		def category3 = new ItemCategory(name: "Clothes", description: "Things you wear").save()
		def category4 = new ItemCategory(name: "Vehicles", description: "Things that have wheels").save()


		def auction1 = new Auction(datePosted: date, dateEnding: date + 1, description: "test 1", status: statusActive, itemCategory: category1).save();
		def auction2 = new Auction(datePosted: date, dateEnding: date + 5, description: "longer 5", status: statusActive, itemCategory: category2).save();
		def auction3 = new Auction(datePosted: date, dateEnding: date + 7, description: "one week", status: statusActive, itemCategory: category3).save();
		def auction4 = new Auction(datePosted: date - 2, dateEnding: date - 1, description: "expired", status: statusExpired, itemCategory: category4).save();
	}
	def destroy = {
	}
}
