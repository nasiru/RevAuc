
<%@ page import="testConnect.City" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'city.label', default: 'City')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-city" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-city" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list city">
			
				<g:if test="${cityInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="city.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${cityInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cityInstance?.countryCode}">
				<li class="fieldcontain">
					<span id="countryCode-label" class="property-label"><g:message code="city.countryCode.label" default="Country Code" /></span>
					
						<span class="property-value" aria-labelledby="countryCode-label"><g:fieldValue bean="${cityInstance}" field="countryCode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cityInstance?.district}">
				<li class="fieldcontain">
					<span id="district-label" class="property-label"><g:message code="city.district.label" default="District" /></span>
					
						<span class="property-value" aria-labelledby="district-label"><g:fieldValue bean="${cityInstance}" field="district"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cityInstance?.population}">
				<li class="fieldcontain">
					<span id="population-label" class="property-label"><g:message code="city.population.label" default="Population" /></span>
					
						<span class="property-value" aria-labelledby="population-label"><g:fieldValue bean="${cityInstance}" field="population"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cityInstance?.id}" />
					<g:link class="edit" action="edit" id="${cityInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
