
<%@ page import="au.edu.unimelb.cis.arch.revauc.AuctionHistory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auctionHistory.label', default: 'AuctionHistory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-auctionHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-auctionHistory" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="price" title="${message(code: 'auctionHistory.price.label', default: 'Price')}" />
					
						<th><g:message code="auctionHistory.auction.label" default="Auction" /></th>
					
						<g:sortableColumn property="bidDate" title="${message(code: 'auctionHistory.bidDate.label', default: 'Bid Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${auctionHistoryInstanceList}" status="i" var="auctionHistoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${auctionHistoryInstance.id}">${fieldValue(bean: auctionHistoryInstance, field: "price")}</g:link></td>
					
						<td>${fieldValue(bean: auctionHistoryInstance, field: "auction")}</td>
					
						<td><g:formatDate date="${auctionHistoryInstance.bidDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${auctionHistoryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
