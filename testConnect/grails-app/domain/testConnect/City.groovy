package testConnect

class City {

	String name
	String countryCode
	String district
	Integer population

	static mapping = {
		version false
	}

	static constraints = {
		name maxSize: 35
		countryCode maxSize: 3
		district maxSize: 20
	}
}
