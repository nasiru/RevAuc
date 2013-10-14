package au.edu.unimelb.cis.arch.revauc

class UserAccount {

	User user
	String password
	String contactDetails
	Integer accountType

	static hasMany = [bids: Bids, auctions: Auction]

	static constraints = {
	}
}
