<%@ page import="au.edu.unimelb.cis.arch.revauc.ItemCategory" %>



<div class="fieldcontain ${hasErrors(bean: itemCategoryInstance, field: 'auction', 'error')} required">
	<label for="auction">
		<g:message code="itemCategory.auction.label" default="Auction" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="auction" name="auction.id" from="${au.edu.unimelb.cis.arch.revauc.Auction.list()}" optionKey="id" required="" value="${itemCategoryInstance?.auction?.id}" class="many-to-one"/>
</div>

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

