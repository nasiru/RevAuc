package au.edu.unimelb.cis.arch.revauc

class Auction {

	Date timeRemaining
	Date datePosted
	String description

	static hasMany = [bids: Bids, auctionHistory: AuctionHistory, itemCategory: ItemCategory, requirements: Requirements]
	static hasOne = [status: Status]

	static constraints = { status unique: true }
}
