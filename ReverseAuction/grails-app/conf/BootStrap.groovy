import au.edu.unimelb.cis.arch.revauc.Auction
import au.edu.unimelb.cis.arch.revauc.ItemCategory
import au.edu.unimelb.cis.arch.revauc.SecRole
import au.edu.unimelb.cis.arch.revauc.SecUserSecRole
import au.edu.unimelb.cis.arch.revauc.Status
import au.edu.unimelb.cis.arch.revauc.User

class BootStrap {

	def springSecurityService

	def init = { servletContext ->
		def date = new Date();

		def userRole = SecRole.findByAuthority("ROLE_USER") ?: new SecRole(authority: "ROLE_USER").save()
		def adminRole = SecRole.findByAuthority("ROLE_ADMIN") ?: new SecRole(authority: "ROLE_ADMIN").save()

		def user1 = new User(username: "user1", password: "password1", contactDetails: "Melbourne", enabled: true).save()
		def user2 = new User(username: "user2", password: "password2", contactDetails: "Sydney", enabled: true).save()
		def admin1 = new User(username: "admin1", password: "password1", contactDetails: "Adelaide", enabled: true).save()

		def statusActive = new Status(category: "Active").save();
		def statusExpired = new Status(category: "Expired").save();
		def statusCompleted = new Status(category: "Completed").save();

		def category1 = new ItemCategory(name: "Food", description: "Things you can eat").save()
		def category2 = new ItemCategory(name: "Electronics", description: "Things that can zap you").save()
		def category3 = new ItemCategory(name: "Clothes", description: "Things you wear").save()
		def category4 = new ItemCategory(name: "Vehicles", description: "Things that have wheels").save()


		def auction1 = new Auction(datePosted: date, dateEnding: date + 1, description: "test 1", status: statusActive, itemCategory: category1, user: user1, minBid: 1234, leader: "---").save();
		def auction2 = new Auction(datePosted: date, dateEnding: date + 5, description: "longer 5", status: statusActive, itemCategory: category2, user: user2, minBid: 663, leader: "---").save();
		def auction3 = new Auction(datePosted: date, dateEnding: date + 7, description: "one week", status: statusActive, itemCategory: category3, user: user1, minBid: 5684, leader: "---").save();
		def auction4 = new Auction(datePosted: date - 2, dateEnding: date - 1, description: "expired", status: statusExpired, itemCategory: category4, user: user2, minBid: 528, leader: "---").save();

		SecUserSecRole.create user1, userRole
		SecUserSecRole.create user2, userRole
		SecUserSecRole.create admin1, adminRole
	}
	def destroy = {
	}
}
