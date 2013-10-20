<%@ page import="au.edu.unimelb.cis.arch.revauc.ItemCategory" %>



<div class="fieldcontain ${hasErrors(bean: itemCategoryInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="itemCategory.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${itemCategoryInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: itemCategoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="itemCategory.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${itemCategoryInstance?.name}"/>
</div>

