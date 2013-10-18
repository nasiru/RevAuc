package au.edu.unimelb.cis.arch.revauc

class Auction {

	Date dateEnding
	Date datePosted
	String description
	Status status

	static hasMany = [bids: Bids, itemCategory: ItemCategory, requirements: Requirements]
	//static hasOne = [auctionHistory: AuctionHistory]

	static constraints = {
		dateEnding validator: { value, auction, errors ->
			if ( value < auction.datePosted ) {
				errors.rejectValue( "dateEnding", "auction.dateEnding.afterDatePosted", "End date cannot be before start date.")
				return false
			}

			return true
		}
	}

	static mapping = {
		bids sort: 'bidDate', order: 'desc'
	}

}
