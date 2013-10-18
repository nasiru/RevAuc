
<%@ page import="au.edu.unimelb.cis.arch.revauc.UserAccount" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userAccount.label', default: 'UserAccount')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-userAccount" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-userAccount" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="accountType" title="${message(code: 'userAccount.accountType.label', default: 'Account Type')}" />
					
						<g:sortableColumn property="contactDetails" title="${message(code: 'userAccount.contactDetails.label', default: 'Contact Details')}" />
					
						<th><g:message code="userAccount.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userAccountInstanceList}" status="i" var="userAccountInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userAccountInstance.id}">${fieldValue(bean: userAccountInstance, field: "accountType")}</g:link></td>
					
						<td>${fieldValue(bean: userAccountInstance, field: "contactDetails")}</td>
					
						<td>${fieldValue(bean: userAccountInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userAccountInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
