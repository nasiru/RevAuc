
<%@ page import="au.edu.unimelb.cis.arch.revauc.Auction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auction.label', default: 'Auction')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-auction" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-auction" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list auction">
			
				<g:if test="${auctionInstance?.auctionHistory}">
				<li class="fieldcontain">
					<span id="auctionHistory-label" class="property-label"><g:message code="auction.auctionHistory.label" default="Auction History" /></span>
					
						<g:each in="${auctionInstance.auctionHistory}" var="a">
						<span class="property-value" aria-labelledby="auctionHistory-label"><g:link controller="auctionHistory" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			
				<g:if test="${auctionInstance?.datePosted}">
				<li class="fieldcontain">
					<span id="datePosted-label" class="property-label"><g:message code="auction.datePosted.label" default="Date Posted" /></span>
					
						<span class="property-value" aria-labelledby="datePosted-label"><g:formatDate date="${auctionInstance?.datePosted}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="auction.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${auctionInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.itemCategory}">
				<li class="fieldcontain">
					<span id="itemCategory-label" class="property-label"><g:message code="auction.itemCategory.label" default="Item Category" /></span>
					
						<g:each in="${auctionInstance.itemCategory}" var="i">
						<span class="property-value" aria-labelledby="itemCategory-label"><g:link controller="itemCategory" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.requirements}">
				<li class="fieldcontain">
					<span id="requirements-label" class="property-label"><g:message code="auction.requirements.label" default="Requirements" /></span>
					
						<g:each in="${auctionInstance.requirements}" var="r">
						<span class="property-value" aria-labelledby="requirements-label"><g:link controller="requirements" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="auction.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:link controller="status" action="show" id="${auctionInstance?.status?.id}">${auctionInstance?.status?.category.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.dateEnding}">
				<li class="fieldcontain">
					<span id="dateEnding-label" class="property-label"><g:message code="auction.dateEnding.label" default="Date Ending" /></span>
					
						<span class="property-value" aria-labelledby="dateEnding-label"><g:formatDate date="${auctionInstance?.dateEnding}" /></span>
					
				</li>
				</g:if>
				
				<li class="fieldcontain">
				<span id="bids-label" class="property-label"><g:message code="auction.bids.label" default="Bids" /></span>
					<span class="property-value" aria-labelledby="bids-label">
					<g:if test="${auctionInstance?.bids}">
						<g:each in="${auctionInstance?.bids?}" var="b">
						    <g:link controller="bids" action="show" id="${b.id}">${b?.price.encodeAsHTML()}</g:link><br/>
						</g:each>
						<br/>
					</g:if>
						<g:link controller="bids" action="create" params="['auction.id': auctionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'bids.label', default: 'Bids')])}</g:link>
						<g:link controller="bids" action="list" >See Bidding History</g:link>
					</span>
				
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${auctionInstance?.id}" />
					<g:link class="edit" action="edit" id="${auctionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
