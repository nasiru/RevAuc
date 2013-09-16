package testConnect

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class Countrylanguage implements Serializable {

	String countryCode
	String language
	String isOfficial
	Float percentage

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append countryCode
		builder.append language
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append countryCode, other.countryCode
		builder.append language, other.language
		builder.isEquals()
	}

	static mapping = {
		id composite: ["countryCode", "language"]
		version false
	}

	static constraints = {
		countryCode maxSize: 3
		language maxSize: 30
		isOfficial maxSize: 2
		percentage scale: 1
	}
}
