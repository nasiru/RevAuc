package au.edu.unimelb.cis.arch.revauc

class User extends SecUser {

	//static hasMany = [userAccount: UserAccount]
	static hasMany = [bids: Bids, auctions: Auction]

	static constraints = {
	}
}
