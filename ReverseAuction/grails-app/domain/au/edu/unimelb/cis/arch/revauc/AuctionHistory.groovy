package au.edu.unimelb.cis.arch.revauc


class AuctionHistory {

	static belongsTo = [auction: Auction]
	static hasMany = [bids: Bids]

	static constraints = {
	}
}
