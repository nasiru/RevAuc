<%@ page import="au.edu.unimelb.cis.arch.revauc.UserAccount" %>



<div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'accountType', 'error')} required">
	<label for="accountType">
		<g:message code="userAccount.accountType.label" default="Account Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="accountType" type="number" value="${userAccountInstance.accountType}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'auctions', 'error')} ">
	<label for="auctions">
		<g:message code="userAccount.auctions.label" default="Auctions" />
		
	</label>
	<g:select name="auctions" from="${au.edu.unimelb.cis.arch.revauc.Auction.list()}" multiple="multiple" optionKey="id" size="5" value="${userAccountInstance?.auctions*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'bids', 'error')} ">
	<label for="bids">
		<g:message code="userAccount.bids.label" default="Bids" />
		
	</label>
	<g:select name="bids" from="${au.edu.unimelb.cis.arch.revauc.Bids.list()}" multiple="multiple" optionKey="id" size="5" value="${userAccountInstance?.bids*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'contactDetails', 'error')} ">
	<label for="contactDetails">
		<g:message code="userAccount.contactDetails.label" default="Contact Details" />
		
	</label>
	<g:textField name="contactDetails" value="${userAccountInstance?.contactDetails}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="userAccount.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${au.edu.unimelb.cis.arch.revauc.User.list()}" optionKey="id" required="" value="${userAccountInstance?.user?.id}" class="many-to-one"/>
</div>

