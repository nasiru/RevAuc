<%@ page import="au.edu.unimelb.cis.arch.revauc.Bids" %>



<div class="fieldcontain ${hasErrors(bean: bidsInstance, field: 'price', 'error')}">

	<g:field name="price" value="${fieldValue(bean: bidsInstance, field: 'price')}"/>
</div>