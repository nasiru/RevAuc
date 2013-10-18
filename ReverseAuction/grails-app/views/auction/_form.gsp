<%@ page import="au.edu.unimelb.cis.arch.revauc.Auction" %>



<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'auctionHistory', 'error')} ">
	<label for="auctionHistory">
		<g:message code="auction.auctionHistory.label" default="Auction History" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${auctionInstance?.auctionHistory?}" var="a">
    <li><g:link controller="auctionHistory" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="auctionHistory" action="create" params="['auction.id': auctionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'auctionHistory.label', default: 'AuctionHistory')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'bids', 'error')} ">
	<label for="bids">
		<g:message code="auction.bids.label" default="Bids" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${auctionInstance?.bids?}" var="b">
    <li><g:link controller="bids" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="bids" action="create" params="['auction.id': auctionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'bids.label', default: 'Bids')])}</g:link>
</li>
</ul>

</div>

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

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="auction.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="status" name="status.id" from="${au.edu.unimelb.cis.arch.revauc.Status.list()}" optionKey="id" required="" value="${auctionInstance?.status?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'dateEnding', 'error')} required">
	<label for="dateEnding">
		<g:message code="auction.dateEnding.label" default="Date Ending" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateEnding" precision="day"  value="${auctionInstance?.dateEnding}"  />
</div>

