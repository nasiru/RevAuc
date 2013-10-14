package au.edu.unimelb.cis.arch.revauc

class User {

	String username
	int accessLevel

	static hasOne = [userAccount: UserAccount]

	static constraints = {
		username (blank:false, nullable:false, size:3..30, matches:"[a-zA-Z0-9_]+")
		accessLevel (blank:false, nullable:false, size:1..3, matches:"[0-9]+")
		userAccount unique:true
	}
}
