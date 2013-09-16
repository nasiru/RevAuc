<%@ page import="testConnect.City" %>



<div class="fieldcontain ${hasErrors(bean: cityInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="city.name.label" default="Name" />
		
	</label>
	<g:textField name="name" maxlength="35" value="${cityInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cityInstance, field: 'countryCode', 'error')} ">
	<label for="countryCode">
		<g:message code="city.countryCode.label" default="Country Code" />
		
	</label>
	<g:textField name="countryCode" maxlength="3" value="${cityInstance?.countryCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cityInstance, field: 'district', 'error')} ">
	<label for="district">
		<g:message code="city.district.label" default="District" />
		
	</label>
	<g:textField name="district" maxlength="20" value="${cityInstance?.district}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cityInstance, field: 'population', 'error')} required">
	<label for="population">
		<g:message code="city.population.label" default="Population" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="population" type="number" value="${cityInstance.population}" required=""/>
</div>

