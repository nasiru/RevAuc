package au.edu.unimelb.cis.arch.revauc

class SecUser {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true, matches:/[a-zA-Z][a-zA-Z0-9]+/, size: 4..12

		password validator: { String password, user, errors ->

			// skip validation of hashed password
			if (password.length() == 60) {
				return true
			}

			if (user.username.equals(password)) {
				errors.rejectValue( "password", "user.password.error.username", "Password must be of size 4-12, have a letter and a number, and is not the same as the Username")
				return false
			}

			if (password.length() < 4 || password.length() > 12 || !password.matches('^.*\\p{Alpha}.*$') || !password.matches('^.*\\p{Digit}.*$')) {
				errors.rejectValue( "password", "user.password.error.username", "Password must be of size 4-12, have a letter and a number, and is not the same as the Username")
				return false
			}
		}
	}

	static mapping = { password column: '`password`' }

	Set<SecRole> getAuthorities() {
		SecUserSecRole.findAllBySecUser(this).collect { it.secRole } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
