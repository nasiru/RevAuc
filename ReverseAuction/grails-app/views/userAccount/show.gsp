
<%@ page import="au.edu.unimelb.cis.arch.revauc.UserAccount" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userAccount.label', default: 'UserAccount')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-userAccount" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-userAccount" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list userAccount">
			
				<g:if test="${userAccountInstance?.accountType}">
				<li class="fieldcontain">
					<span id="accountType-label" class="property-label"><g:message code="userAccount.accountType.label" default="Account Type" /></span>
					
						<span class="property-value" aria-labelledby="accountType-label"><g:fieldValue bean="${userAccountInstance}" field="accountType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userAccountInstance?.auctions}">
				<li class="fieldcontain">
					<span id="auctions-label" class="property-label"><g:message code="userAccount.auctions.label" default="Auctions" /></span>
					
						<g:each in="${userAccountInstance.auctions}" var="a">
						<span class="property-value" aria-labelledby="auctions-label"><g:link controller="auction" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userAccountInstance?.bids}">
				<li class="fieldcontain">
					<span id="bids-label" class="property-label"><g:message code="userAccount.bids.label" default="Bids" /></span>
					
						<g:each in="${userAccountInstance.bids}" var="b">
						<span class="property-value" aria-labelledby="bids-label"><g:link controller="bids" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userAccountInstance?.contactDetails}">
				<li class="fieldcontain">
					<span id="contactDetails-label" class="property-label"><g:message code="userAccount.contactDetails.label" default="Contact Details" /></span>
					
						<span class="property-value" aria-labelledby="contactDetails-label"><g:fieldValue bean="${userAccountInstance}" field="contactDetails"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userAccountInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="userAccount.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${userAccountInstance?.user?.id}">${userAccountInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${userAccountInstance?.id}" />
					<g:link class="edit" action="edit" id="${userAccountInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
