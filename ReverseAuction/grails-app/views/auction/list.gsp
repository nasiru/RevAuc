
<%@ page import="au.edu.unimelb.cis.arch.revauc.Auction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auction.label', default: 'Auction')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-auction" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-auction" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="datePosted" title="${message(code: 'auction.datePosted.label', default: 'Date Posted')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'auction.description.label', default: 'Description')}" />
					
						<th><g:message code="auction.status.category" default="Status" /></th>
					
						<g:sortableColumn property="dateEnding" title="${message(code: 'auction.dateEnding.label', default: 'Date Ending')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${auctionInstanceList}" status="i" var="auctionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${auctionInstance.id}">${fieldValue(bean: auctionInstance, field: "datePosted")}</g:link></td>
					
						<td>${fieldValue(bean: auctionInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: auctionInstance, field: "status.category")}</td>
					
						<td><g:formatDate date="${auctionInstance.dateEnding}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${auctionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
