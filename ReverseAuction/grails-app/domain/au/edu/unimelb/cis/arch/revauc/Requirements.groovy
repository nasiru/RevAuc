package au.edu.unimelb.cis.arch.revauc

class Requirements {

	String name
	String description
	String options

	static belongsTo = [auction: Auction]

	static constraints = {
	}
}
