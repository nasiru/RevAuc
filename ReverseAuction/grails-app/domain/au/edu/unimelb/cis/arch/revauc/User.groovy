package au.edu.unimelb.cis.arch.revauc

class User {

	String username

	static hasMany = [userAccount: UserAccount]

	static constraints = {
		username (blank:false, nullable:false, size:3..30, matches:"[a-zA-Z0-9_]+")
	}
}
