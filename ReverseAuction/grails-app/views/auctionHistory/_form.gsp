<%@ page import="au.edu.unimelb.cis.arch.revauc.AuctionHistory" %>



<div class="fieldcontain ${hasErrors(bean: auctionHistoryInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="auctionHistory.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: auctionHistoryInstance, field: 'price')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: auctionHistoryInstance, field: 'auction', 'error')} required">
	<label for="auction">
		<g:message code="auctionHistory.auction.label" default="Auction" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="auction" name="auction.id" from="${au.edu.unimelb.cis.arch.revauc.Auction.list()}" optionKey="id" required="" value="${auctionHistoryInstance?.auction?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auctionHistoryInstance, field: 'bidDate', 'error')} required">
	<label for="bidDate">
		<g:message code="auctionHistory.bidDate.label" default="Bid Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="bidDate" precision="day"  value="${auctionHistoryInstance?.bidDate}"  />
</div>

