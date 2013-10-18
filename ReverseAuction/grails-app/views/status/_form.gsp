<%@ page import="au.edu.unimelb.cis.arch.revauc.Status" %>



<div class="fieldcontain ${hasErrors(bean: statusInstance, field: 'category', 'error')} ">
	<label for="category">
		<g:message code="status.category.label" default="Category" />
		
	</label>
	<g:textField name="category" value="${statusInstance?.category}"/>
</div>

