<%@ page import="au.edu.unimelb.cis.arch.revauc.Requirements" %>



<div class="fieldcontain ${hasErrors(bean: requirementsInstance, field: 'auction', 'error')} required">
	<label for="auction">
		<g:message code="requirements.auction.label" default="Auction" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="auction" name="auction.id" from="${au.edu.unimelb.cis.arch.revauc.Auction.list()}" optionKey="id" required="" value="${requirementsInstance?.auction?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: requirementsInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="requirements.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${requirementsInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: requirementsInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="requirements.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${requirementsInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: requirementsInstance, field: 'options', 'error')} ">
	<label for="options">
		<g:message code="requirements.options.label" default="Options" />
		
	</label>
	<g:textField name="options" value="${requirementsInstance?.options}"/>
</div>

