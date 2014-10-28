<link rel="stylesheet" href="<c:url value="/resources/shared/css/custom-navbar.css" />">

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
				<li <% if(url.startsWith("/card-game")){ %> class="active" <% } %>><a href="<c:url value="/card-game"/>">Card Game</a></li>
				<li <% if(url.startsWith("/mood-tracking")){ %> class="active" <% } %>><a href="<c:url value="/mood-tracking"/>">Express Your Mood</a></li>
				<li <% if(url.startsWith("/forums")){ %> class="active" <% } %>><a href="<c:url value="/forums" />">Forums</a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li <% if(url.startsWith("/personalisation")){ %> class="active" <% } %>><a href="<c:url value="/personalisation" />">Dashboard</a></li>
				</sec:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
				<li><a href="<c:url value="/login"/>">Login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<li id="notifications" class="dropdown" ng-controller="NotificationCtrl" ng-init="listUnreadNotification()" ng-click="setMessagesRead()"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><span class="glyphicon glyphicon-globe"></span> <span class="badge alert-danger" ng-show="unreadNotifications.length > 0">{{unreadNotifications.length}}</span> <span class="caret"></span></a>
					<ul class="dropdown-menu dropdown-notifications" role="menu">
						<li ng-repeat="unread in showUnreadNotifications"><a href="<c:url value="/{{unread.url}}"/>" class="new-line-overflow-dropdown" ng-show="unread.url != ''">{{unread.message}}</a>
						<a href="<c:url value=""/>" class="new-line-overflow-dropdown" ng-show="unread.url == ''">{{unread.message}}</a></li>
						<li class="text-center" ng-show="showUnreadNotifications.length == 0"><i>No new notifications</i></li>
						<li class="divider"></li>
						<li><a href="<c:url value="/notification"/>" class="text-center">All Notifications</a></li>
					</ul>
				</li>
				<li><a href="<c:url value="/user/profile"/>"><sec:authentication property="principal.username" /></a></li>
				<li><p class="navbar-text" id="user_points"><span class="text-warning glyphicon glyphicon-star"/></p></li>
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
				</sec:authorize>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>
