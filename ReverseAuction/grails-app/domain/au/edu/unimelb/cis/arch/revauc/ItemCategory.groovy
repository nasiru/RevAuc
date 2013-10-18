package au.edu.unimelb.cis.arch.revauc

class ItemCategory {

	static belongsTo = [auction: Auction]

	String name
	String description

	static constraints = {
	}
}
