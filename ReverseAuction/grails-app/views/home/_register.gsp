<%@ page import="au.edu.unimelb.cis.arch.revauc.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:passwordField name="password" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'contactDetails', 'error')} required">
	<label for="contactDetails">
		<g:message code="user.contactDetails.label" default="Contact Details" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="contactDetails" maxlength="100" required="" value="${userInstance?.contactDetails}"/>
</div>

<g:hiddenField name="secRole.id" value="${1}"/>

