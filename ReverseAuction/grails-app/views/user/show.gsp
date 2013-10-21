
<%@ page import="au.edu.unimelb.cis.arch.revauc.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<g:render template="/home/navbartop"/>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${userInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.accountExpired}">
				<li class="fieldcontain">
					<span id="accountExpired-label" class="property-label"><g:message code="user.accountExpired.label" default="Account Expired" /></span>
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${userInstance?.accountExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.accountLocked}">
				<li class="fieldcontain">
					<span id="accountLocked-label" class="property-label"><g:message code="user.accountLocked.label" default="Account Locked" /></span>
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${userInstance?.accountLocked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.contactDetails}">
				<li class="fieldcontain">
					<span id="contactDetails" class="property-label"><g:message code="user.contactDetails.label" default="Contact Details" /></span>
					
						<span class="property-value" aria-labelledby="contactDetails"><g:fieldValue bean="${userInstance}" field="contactDetails"/></span>
					
				</li>
				</g:if>
				
			<sec:ifAllGranted roles="ROLE_ADMIN">
				<g:if test="${userInstance?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="user.enabled.label" default="Enabled" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${userInstance?.enabled}" /></span>
					
				</li>
				</g:if>
			</sec:ifAllGranted>
			
				<g:if test="${userInstance?.passwordExpired}">
				<li class="fieldcontain">
					<span id="passwordExpired-label" class="property-label"><g:message code="user.passwordExpired.label" default="Password Expired" /></span>
					
						<span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${userInstance?.passwordExpired}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			
			<div id="list-auction" class="content scaffold-list" role="main">
				<h1>Bidding History</h1>
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="auction.description" title="${message(code: 'auction.description.label', default: 'Description')}" />
						
							<g:sortableColumn property="bids.price" title="${message(code: 'bid.price.label', default: 'Bid Price')}" />
							
							<g:sortableColumn property="bids.bidDate" title="${message(code: 'bid.date.label', default: 'Bid Date')}" />
						
							<g:sortableColumn property="auction.minBid" title="${message(code: 'bid.lead.label', default: 'Current Leader')}" />
						
							<g:sortableColumn property="auction.status.category" title="${message(code: 'auction.status.category', default: 'Status')}" />
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${userInstance?.bids?}" status="i" var="userBid">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td><g:link controller="auction" action="show" id="${userBid.auction.id}">${fieldValue(bean: userBid, field: "auction.description")}</g:link></td>
						
							<td>${fieldValue(bean: userBid, field: "price")}</td>
							
							<td>${fieldValue(bean: userBid, field: "bidDate")}</td>
						
							<td>${fieldValue(bean: userBid, field: "auction.minBid")}</td>
						
							<td>${fieldValue(bean: userBid, field: "auction.status.category")}</td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
			</div>
				
			<sec:ifAllGranted roles="ROLE_ADMIN">
				<g:form>
					<fieldset class="buttons">
						<g:hiddenField name="id" value="${userInstance?.id}" />
						<g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
			</sec:ifAllGranted>
			<sec:ifAllGranted roles="ROLE_USER">
				<g:if test="${userid == userInstance.id }">
				<g:form>
					<fieldset class="buttons">
						<g:hiddenField name="id" value="${userInstance?.id}" />
						<g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
				</g:if>
			</sec:ifAllGranted>
		</div>
	</body>
</html>
