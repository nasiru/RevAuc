<%@ page import="au.edu.unimelb.cis.arch.revauc.Bids" %>

<%@ page import="au.edu.unimelb.cis.arch.revauc.Auction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auction.label', default: 'Auction')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-auction" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>		
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<g:render template="/home/navbartop"/>				
			</ul>
		</div>
		<div id="show-auction" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list auction">
			
				<g:if test="${auctionInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="auction.user.label" default="Owner" /></span>
						
					<g:link class="property-value" aria-labelledby="user-label" controller="user" action="show" id="${auctionInstance.user.id}">${fieldValue(bean: auctionInstance, field: "user.username")}</g:link>
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.datePosted}">
				<li class="fieldcontain">
					<span id="datePosted-label" class="property-label"><g:message code="auction.datePosted.label" default="Date Posted" /></span>
					
						<span class="property-value" aria-labelledby="datePosted-label"><g:formatDate date="${auctionInstance?.datePosted}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="auction.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${auctionInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.itemCategory}">
				<li class="fieldcontain">
					<span id="itemCategory-label" class="property-label"><g:message code="auction.itemCategory.label" default="Item Category" /></span>
					
						<g:each in="${auctionInstance.itemCategory}" var="i">
						<span class="property-value" aria-labelledby="itemCategory-label">${i?.name.encodeAsHTML()}</span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.requirements}">
				<li class="fieldcontain">
					<span id="requirements-label" class="property-label"><g:message code="auction.requirements.label" default="Requirements" /></span>
					
						<g:each in="${auctionInstance.requirements}" var="r">
						<span class="property-value" aria-labelledby="requirements-label">${r?.description.encodeAsHTML()}</span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="auction.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label">${auctionInstance?.status?.category.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${auctionInstance?.dateEnding}">
				<li class="fieldcontain">
					<span id="dateEnding-label" class="property-label"><g:message code="auction.dateEnding.label" default="Date Ending" /></span>
					
						<span class="property-value" aria-labelledby="dateEnding-label"><g:formatDate date="${auctionInstance?.dateEnding}" /></span>
					
				</li>
				</g:if>
				
				<li class="fieldcontain">
				<span id="bids-label" class="property-label"><g:message code="auction.bids.label" default="Lowest Bid" /></span>
					<span class="property-value" aria-labelledby="bids-label">
					<g:if test="${auctionInstance?.bids}">
						${minBid}
					</g:if>		
					</span>
				</li>
				
			
			</ol>
				
				
				<g:if test="${auctionInstance?.status.category == 'Active'}">
					<g:if test="${userid != auctionInstance.user.id }">
						<ol  style="padding:0px; margin:0px; list-style-type: none">
							<li class="fieldcontain">
								<span class="property-value">
									
											<g:form controller="bids" action="save" >
												<g:hiddenField name="auctionId" value="${auctionInstance?.id}"/>
												<g:render template="/bids/form"/>
												<br>
												<g:submitButton name="create"value="${message(code: 'bids.place', default: 'Place Bid')}"/>
											</g:form>	
																
								</span>
							</li>
						</ol>
						
						<sec:ifAllGranted roles="ROLE_ADMIN">
							<ol  style="padding:0px; margin:0px; list-style-type: none">
								<li class="fieldcontain">
									<span class="property-value">
										<g:form controller="auction" action="close">
											<g:hiddenField name="auctionId" value="${auctionInstance?.id}"/>
											<g:submitButton name="close" value="${message(code: 'auction.end', default: 'Close Auction')}"/>
										</g:form>
									</span>
								</li>
							</ol>
						</sec:ifAllGranted>
					</g:if>
					<g:else>
						<sec:ifAnyGranted roles="ROLE_USER, ROLE_ADMIN">
							<ol  style="padding:0px; margin:0px; list-style-type: none">
								<li class="fieldcontain">
									<span class="property-value">
										<g:form controller="auction" action="close">
											<g:hiddenField name="auctionId" value="${auctionInstance?.id}"/>
											<g:submitButton name="close" value="${message(code: 'auction.end', default: 'Close Auction')}"/>
										</g:form>
									</span>
								</li>
							</ol>
						</sec:ifAnyGranted>
					</g:else>
				</g:if>		
			
			
			
			<div id="list-bids" class="content scaffold-list" role="main">
				<h1>Bidding History</h1>
				<table>
					<thead>
						<tr>
						
							<th>Price</th>
						
							<th>Bid Date</th>
							
							<th>Bidder</th>
						
						</tr>
					</thead>
					<tbody>
						<g:each in="${auctionInstance?.bids?}" status="i" var="bidsInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								
								<td>${fieldValue(bean: bidsInstance, field: "price")}</td>
							
								<td>${fieldValue(bean: bidsInstance, field: "bidDate")}</td>
								
								<td><g:link controller="user" action="show" id="${bidsInstance.user.id}">${fieldValue(bean: bidsInstance, field: "user.username")}</g:link></td>
							
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</div>
		<sec:ifAllGranted roles="ROLE_ADMIN">
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${auctionInstance?.id}" />
					<g:link class="edit" action="edit" id="${auctionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</sec:ifAllGranted>
		<sec:ifAllGranted roles="ROLE_USER">
			<g:if test="${userid == auctionInstance.user.id && auctionInstance?.status.category == 'Active'}">
				<g:form>
					<fieldset class="buttons">
						<g:hiddenField name="id" value="${auctionInstance?.id}" />
						<g:link class="edit" action="edit" id="${auctionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
			</g:if>
		</sec:ifAllGranted>
	</body>
</html>
