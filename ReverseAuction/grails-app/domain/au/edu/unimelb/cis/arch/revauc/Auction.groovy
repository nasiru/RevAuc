package au.edu.unimelb.cis.arch.revauc

import org.apache.commons.collections.FactoryUtils
import org.apache.commons.collections.list.LazyList

class Auction {

	Date dateEnding
	Date datePosted
	String description
	Status status
	ItemCategory itemCategory
	List requirements = new ArrayList()

	BigDecimal minBid
	String leader

	static hasMany = [bids: Bids, requirements: Requirements]
	static belongsTo = [user: User]
	//static hasOne = [auctionHistory: AuctionHistory]


	static constraints = {
		dateEnding validator: { value, auction, errors ->
			def now = new Date()

			if ( value <= auction.datePosted) {
				errors.rejectValue( "dateEnding", "auction.dateEnding.afterDatePosted", "End date cannot be before/at the start or current date")
				return false
			}

			return true
		}

		description blank: false, nullable: false, size: 1..50

	}

	static mapping = {
		bids sort: 'bidDate', order: 'desc'
		requirements cascade: "all-delete-orphan"
	}

	def getRequirementsList() {
		return LazyList.decorate(
		requirements,
		FactoryUtils.instantiateFactory(Requirements.class))
	}

}
