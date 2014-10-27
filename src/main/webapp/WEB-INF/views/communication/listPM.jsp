<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html ng-app="moodFlip">
<head>
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/comm/css/pm.css" />">
</head>
<body>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>
	
	<div class="container">
	
	<div ng-controller="PrivateMessageCtrl" ng-init="setReceiverId(<c:out value="${receiver.id}" />)">


			<div class="row">
				<div class="col-md-12">
					<h3>
						Private Message - <strong>${receiver.username}</strong>
					</h3>
				</div>
			</div>

			<div class="chat-texts">
			<div class="row" ng-repeat="message in messages">
			<div ng-if="message.sender.username == '<sec:authentication property="principal.username" />'">
				<div class="col-md-2">
					<img src="<c:url value="/resources/comm/pic/default_avatar.png" />"
						alt="avatar" class="img-circle user-pic center-block">
					<h4 class="text-center">{{message.sender.username}}</h4>
				</div>
				<div class="col-md-8">
					<div class="popover right">
						<div class="arrow"></div>
						<h3 class="popover-title text-right">{{message.createdAt}}</h3>
						<div class="popover-content">
							<p class="newLineOverflow">{{message.content}}</p>
						</div>
					</div>
				</div>
			</div>
			<div ng-if="message.sender.username != '<sec:authentication property="principal.username" />'">
				<div class="col-md-offset-2 col-md-8">
					<div class="popover left">
						<div class="arrow"></div>
						<h3 class="popover-title text-right">{{message.createdAt}}</h3>
						<div class="popover-content">
							<p class="newLineOverflow">{{message.content}}</p>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<img src="<c:url value="/resources/comm/pic/default_avatar.png" />"
						alt="avatar" class="img-circle user-pic center-block">
					<h4 class="text-center">{{message.sender.username}}</h4>
				</div>
			</div>
			</div>
			</div>

			<div class="row message-input">
				<div class="col-md-offset-2 col-md-8">
					<form ng-submit="send()">
					<div class="input-group">
						<input type="text" class="form-control" ng-model="message" autofocus>
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">Send</button>
						</span>
					</div>
					<!-- /input-group -->
					</form>
				</div>
			</div>
		</div>
	
	</div>

</body>

<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>

<%@ include file="/WEB-INF/views/angularjs/include-js.jsp"%>

</html>