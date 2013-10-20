package au.edu.unimelb.cis.arch.revauc

class ItemCategory {

	String name
	String description

	static constraints = {
		name size: 1..20, blank: false
	}
}
