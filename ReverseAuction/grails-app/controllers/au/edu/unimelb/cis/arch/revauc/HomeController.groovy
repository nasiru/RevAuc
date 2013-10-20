package au.edu.unimelb.cis.arch.revauc

class HomeController {

	def springSecurityService

	def index = { [user: currentUser() != null ? currentUser().id : 1] }

	private currentUser() {

		if (springSecurityService.isLoggedIn())
			User.get(springSecurityService.principal.id)
		else null
	}
}
