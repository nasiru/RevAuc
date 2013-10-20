<%@ page import="au.edu.unimelb.cis.arch.revauc.Auction" %>

<g:if test="${auctionInstance.id != null}">
	<div class="fieldcontain ${hasErrors(bean: bidsInstance, field: 'price', 'error')}">
		<label for="price">
			<g:message code="auction.datePosted.label" default="Date Posted" />
		</label>
		
		${auctionInstance.datePosted}
	</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="auction.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${auctionInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'itemCategory')} ">
	<label for="itemCategory">
		<g:message code="auction.itemCategory.label" default="Item Category" />
		
	</label>
	
	<g:select name="itemCategory.id"
          from="${au.edu.unimelb.cis.arch.revauc.ItemCategory.list()}"
          value="${auctionInstance?.itemCategory?.id ?: 1}"
          optionKey="id" optionValue="name" />
</div>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'dateEnding', 'error')} required">
	<label for="dateEnding">
		<g:message code="auction.dateEnding.label" default="Date Ending" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateEnding" precision="day" years="${(new Date().year + 1900)..(new Date().year + 1901)}" value="${auctionInstance?.dateEnding}"  />
</div>

<g:if test="${auctionInstance.id == null}">
	<div class="fieldcontain ${hasErrors(bean: bidsInstance, field: 'price', 'error')}">
		<label for="price">
			<g:message code="bids.starting.price" default="Starting Price" />
		</label>
		
		<g:field type="BigDecimal" name="price" value="${fieldValue(bean: bidsInstance, field: 'price')}"/>
	</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: auctionInstance, field: 'requirements', 'error')} ">
	<label for="requirements">
		<g:message code="auction.requirements.label" default="Requirements" />
		
	</label>
	
<g:render template="requirements" model="['auctionInstance':auctionInstance]" />

</div>
