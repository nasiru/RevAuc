<%@ page import="au.edu.unimelb.cis.arch.revauc.Bids" %>



<div class="fieldcontain ${hasErrors(bean: bidsInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="bids.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: bidsInstance, field: 'price')}" required=""/>
</div>