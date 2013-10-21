<li><g:link controller="user" class="list" action="list"><g:message code="default.list.label" args="['User']" /></g:link></li>
<li><g:link controller="auction" class="list" action="list"><g:message code="default.list.label" args="['Auction']" /></g:link></li>
				
<sec:ifNotLoggedIn>
	<li><g:link controller="login">Login</g:link></li>
</sec:ifNotLoggedIn>
<sec:ifLoggedIn>
	<li><g:link controller="user" action="show" id="${userid}">My Account</g:link></li>
	<li><g:link controller="Logout">Logout <sec:username/></g:link></li>
</sec:ifLoggedIn>	