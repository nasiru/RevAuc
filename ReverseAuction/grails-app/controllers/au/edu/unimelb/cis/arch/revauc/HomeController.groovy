package au.edu.unimelb.cis.arch.revauc

class HomeController {

	def springSecurityService

	def index() {
		[userid: currentUser() != null ? currentUser().id : 1]
	}

	private currentUser() {

		if (springSecurityService.isLoggedIn())
			User.get(springSecurityService.principal.id)
		else null
	}

	def save() {
		def userInstance = new User(params)

		if (!userInstance.save(flush: true)) {
			render(view: "index", model: [userInstance: userInstance, userid: currentUser() != null ? currentUser().id : 1])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'user.label', default: 'User'),
			userInstance.id
		])

		SecUserSecRole.create userInstance, SecRole.findById(params.secRole.id)
		redirect(uri: "/", id: userInstance.id)
	}
}
