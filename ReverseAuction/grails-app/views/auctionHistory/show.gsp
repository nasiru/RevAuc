
<%@ page import="au.edu.unimelb.cis.arch.revauc.AuctionHistory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auctionHistory.label', default: 'AuctionHistory')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-auctionHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-auctionHistory" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list auctionHistory">
			
				<g:if test="${auctionHistoryInstance?.price}">
				<li class="fieldcontain">
					<span id="price-label" class="property-label"><g:message code="auctionHistory.price.label" default="Price" /></span>
					
						<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${auctionHistoryInstance}" field="price"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${auctionHistoryInstance?.auction}">
				<li class="fieldcontain">
					<span id="auction-label" class="property-label"><g:message code="auctionHistory.auction.label" default="Auction" /></span>
					
						<span class="property-value" aria-labelledby="auction-label"><g:link controller="auction" action="show" id="${auctionHistoryInstance?.auction?.id}">${auctionHistoryInstance?.auction?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${auctionHistoryInstance?.bidDate}">
				<li class="fieldcontain">
					<span id="bidDate-label" class="property-label"><g:message code="auctionHistory.bidDate.label" default="Bid Date" /></span>
					
						<span class="property-value" aria-labelledby="bidDate-label"><g:formatDate date="${auctionHistoryInstance?.bidDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${auctionHistoryInstance?.id}" />
					<g:link class="edit" action="edit" id="${auctionHistoryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
