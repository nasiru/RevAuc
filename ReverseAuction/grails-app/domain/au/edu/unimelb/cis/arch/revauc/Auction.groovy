package au.edu.unimelb.cis.arch.revauc

class Auction {

	Date dateEnding
	Date datePosted
	String description
	Status status

	static hasMany = [bids: Bids, auctionHistory: AuctionHistory, itemCategory: ItemCategory, requirements: Requirements]

	static constraints = {
		dateEnding validator: { value, auction, errors ->
			if ( value < auction.datePosted ) {
				errors.rejectValue( "dateEnding", "auction.dateEnding.afterDatePosted", "End date cannot be after start date.")
				return false
			}

			return true
		}
	}
}
