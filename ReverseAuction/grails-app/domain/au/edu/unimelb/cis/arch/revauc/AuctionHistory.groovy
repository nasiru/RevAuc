package au.edu.unimelb.cis.arch.revauc


class AuctionHistory {

	Date bidDate
	BigDecimal price

	static constraints = {
		price (nullable: false, min: 0.0, max: 9999.99, scale: 2)
	}
}
