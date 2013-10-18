package au.edu.unimelb.cis.arch.revauc

class Bids {

	BigDecimal price
	Date bidDate

	static belongsTo = [auction: Auction]//, userAccount: UserAccount]

	static constraints = {
		price (nullable: false, min: 0.0, max: 9999.99, scale: 2)

	}

}
