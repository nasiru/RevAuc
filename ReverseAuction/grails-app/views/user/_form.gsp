<%@ page import="au.edu.unimelb.cis.arch.revauc.User" %>



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
	<g:textField name="contactDetails" maxlength="100" required="" value="${userInstance?.contactDetails}"/>
</div>

<sec:ifAllGranted roles="ROLE_ADMIN">
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="user.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="user.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'auctions', 'error')} ">
	<label for="auctions">
		<g:message code="user.auctions.label" default="Auctions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.auctions?}" var="a">
    <li><g:link controller="auction" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="auction" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'auction.label', default: 'Auction')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'bids', 'error')} ">
	<label for="bids">
		<g:message code="user.bids.label" default="Bids" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.bids?}" var="b">
    <li><g:link controller="bids" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="bids" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'bids.label', default: 'Bids')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="user.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${userInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="user.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />
</div>

</sec:ifAllGranted>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'secrole')} ">
	<label for="secrole">
		<g:message code="user.secrole.label" default="Security Role" />
		
	</label>
	
	<g:select name="secRole.id"
          from="${au.edu.unimelb.cis.arch.revauc.SecRole.list()}"
          value="${1}"
          optionKey="id" optionValue="authority" />
</div>

