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
		username blank: false, unique: true, matches:/[a-zA-Z][a-zA-Z0-9]+/, size: 5..12

		password validator: { String password, user ->
			if (user.username && user.username.equals(password)) {
				return 'user.password.error.username'
			}

			if (password && password.length() >= 8 && password.length() <= 16 &&
			(!password.matches('^.*\\p{Alpha}.*$') ||
			!password.matches('^.*\\p{Digit}.*$'))) {
				return 'user.password.error.username'
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
