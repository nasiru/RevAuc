<%@ page import="au.edu.unimelb.cis.arch.revauc.Bids" %>

<div class="fieldcontain ${hasErrors(bean: bidsInstance, field: 'price', 'error')}">
	<g:if test="${auctionInstance?.status.category == 'Active'}">
		<g:field type="BigDecimal" name="price" value="${fieldValue(bean: bidsInstance, field: 'price')}"/>
	</g:if>
</div>