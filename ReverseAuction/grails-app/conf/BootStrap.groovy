import au.edu.unimelb.cis.arch.revauc.Auction
import au.edu.unimelb.cis.arch.revauc.Status

class BootStrap {

	def init = { servletContext ->
		def date = new Date();
		def statusActive = new Status(category: "Active").save();
		def statusExpired = new Status(category: "Expired").save();

		def auction1 = new Auction(datePosted: date, dateEnding: date + 1, description: "test 1", status: statusActive).save();
		def auction2 = new Auction(datePosted: date, dateEnding: date + 5, description: "longer 5", status: statusActive).save();
		def auction3 = new Auction(datePosted: date, dateEnding: date + 7, description: "one week", status: statusActive).save();
		def auction4 = new Auction(datePosted: date - 2, dateEnding: date - 1, description: "expired", status: statusExpired).save();
	}
	def destroy = {
	}
}
