<%@ page import="au.edu.unimelb.cis.arch.revauc.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" maxlength="30" pattern="${userInstance.constraints.username.matches}" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userAccount', 'error')} ">
	<label for="userAccount">
		<g:message code="user.userAccount.label" default="User Account" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.userAccount?}" var="u">
    <li><g:link controller="userAccount" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="userAccount" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'userAccount.label', default: 'UserAccount')])}</g:link>
</li>
</ul>

</div>

