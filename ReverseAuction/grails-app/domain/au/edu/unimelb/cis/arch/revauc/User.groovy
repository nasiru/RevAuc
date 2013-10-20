package au.edu.unimelb.cis.arch.revauc

class User extends SecUser {

	//static hasMany = [userAccount: UserAccount]
	String contactDetails

	static hasMany = [bids: Bids, auctions: Auction]

	static constraints = {
		contactDetails size: 5..100, blank: false
	}
}
