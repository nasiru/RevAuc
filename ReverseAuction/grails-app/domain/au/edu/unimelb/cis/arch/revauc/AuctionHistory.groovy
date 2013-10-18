package au.edu.unimelb.cis.arch.revauc


class AuctionHistory {

	static belongsTo = [auction: Auction]

	Date bidDate
	BigDecimal price

	static constraints = {
		price (nullable: false, min: 0.0, max: 9999.99, scale: 2)
	}
}
