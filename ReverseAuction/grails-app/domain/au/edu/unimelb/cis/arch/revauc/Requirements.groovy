package au.edu.unimelb.cis.arch.revauc

class Requirements {

	static belongsTo = [auction: Auction]

	static constraints = { description blank:false, size: 5..50 }

	static mapping = { index column:"requirements_index" }


	String description
	int index
	boolean deleted
	//static transients = ['deleted']
}
