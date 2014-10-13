<% String url = request.getAttribute("javax.servlet.forward.servlet_path").toString(); %>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/" />">MoodFlip</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li <% if(url.equals("/card-game")){ %> class="active" <% } %>><a href="<c:url value="/card-game"/>">Card Game</a></li>
				<li <% if(url.equals("/mood-tracking")){ %> class="active" <% } %>><a href="<c:url value="/mood-tracking"/>">Express Your Mood</a></li>
				<li <% if(url.startsWith("/forums")){ %> class="active" <% } %>><a href="<c:url value="/forums" />">Forums</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
				<li><a href="<c:url value="/login"/>">Login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<li><a href=""><sec:authentication property="principal.username" /></a></li>
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
				</sec:authorize>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>

    