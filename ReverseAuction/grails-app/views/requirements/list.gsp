
<%@ page import="au.edu.unimelb.cis.arch.revauc.Requirements" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'requirements.label', default: 'Requirements')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-requirements" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-requirements" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="requirements.auction.label" default="Auction" /></th>
					
						<g:sortableColumn property="description" title="${message(code: 'requirements.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'requirements.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="options" title="${message(code: 'requirements.options.label', default: 'Options')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${requirementsInstanceList}" status="i" var="requirementsInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${requirementsInstance.id}">${fieldValue(bean: requirementsInstance, field: "auction")}</g:link></td>
					
						<td>${fieldValue(bean: requirementsInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: requirementsInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: requirementsInstance, field: "options")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${requirementsInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
