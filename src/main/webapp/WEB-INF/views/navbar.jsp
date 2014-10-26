<% String url = request.getAttribute("javax.servlet.forward.servlet_path").toString(); %>

<div class="navbar navbar-inverse navbar-default navbar-fixed-top" role="navigation">
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
				<li <% if(url.startsWith("/card-game")){ %> class="active" <% } %>><a href="<c:url value="/card-game"/>">Card Game</a></li>
				<li <% if(url.startsWith("/mood-tracking")){ %> class="active" <% } %>><a href="<c:url value="/mood-tracking"/>">Express Your Mood</a></li>
				<li <% if(url.startsWith("/forums")){ %> class="active" <% } %>><a href="<c:url value="/forums" />">Forums</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
				<li><a href="<c:url value="/login"/>">Login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<li id="notifications" class="dropdown" ng-controller="NotificationCtrl" ng-init="listUnreadNotification()" ng-click="setMessagesRead()"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><span class="glyphicon glyphicon-globe"></span> <span class="badge" ng-show="unreadNotifications.length > 0">{{unreadNotifications.length}}</span> <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li ng-repeat="unread in showUnreadNotifications"><a href="<c:url value="{{unread.url}}"/>">{{unread.message}}</a></li>
						<li class="text-center" ng-show="showUnreadNotifications.length == 0">No new notifications</li>
						<li class="divider"></li>
						<li><a href="<c:url value="/notification"/>" class="text-center">All Notifications</a></li>
					</ul>
				</li>
				<li><a href=""><sec:authentication property="principal.username" /></a></li>
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
				</sec:authorize>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>