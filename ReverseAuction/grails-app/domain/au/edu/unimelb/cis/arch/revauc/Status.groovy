package au.edu.unimelb.cis.arch.revauc

class Status {

	String category

	static constraints = { category unique: true, size: 5..20 }
}
