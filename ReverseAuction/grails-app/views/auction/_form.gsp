<%@ page import="au.edu.unimelb.cis.arch.revauc.Auction" %>


<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="auction.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${auctionInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'itemCategory', 'error')} ">
	<label for="itemCategory">
		<g:message code="auction.itemCategory.label" default="Item Category" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${auctionInstance?.itemCategory?}" var="i">
    <li><g:link controller="itemCategory" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="itemCategory" action="create" params="['auction.id': auctionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'itemCategory.label', default: 'ItemCategory')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'requirements', 'error')} ">
	<label for="requirements">
		<g:message code="auction.requirements.label" default="Requirements" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${auctionInstance?.requirements?}" var="r">
    <li><g:link controller="requirements" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="requirements" action="create" params="['auction.id': auctionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'requirements.label', default: 'Requirements')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'dateEnding', 'error')} required">
	<label for="dateEnding">
		<g:message code="auction.dateEnding.label" default="Date Ending" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateEnding" precision="day"  value="${auctionInstance?.dateEnding}"  />
</div>

