package testConnect

class Country {

	String code
	String name
	String continent
	String region
	Float surfaceArea
	Short indepYear
	Integer population
	Float lifeExpectancy
	Float gnp
	Float gnpold
	String localName
	String governmentForm
	String headOfState
	Integer capital
	String code2

	static mapping = {
		id name: "code", generator: "assigned"
		version false
	}

	static constraints = {
		code maxSize: 3
		name maxSize: 52
		continent maxSize: 13
		region maxSize: 26
		indepYear nullable: true
		lifeExpectancy nullable: true, scale: 1
		gnp nullable: true
		gnpold nullable: true
		localName maxSize: 45
		governmentForm maxSize: 45
		headOfState nullable: true, maxSize: 60
		capital nullable: true
		code2 maxSize: 2
	}
}
