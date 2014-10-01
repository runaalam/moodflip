<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html ng-app="moodFlip">
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>Notification</h3>
	
	<div ng-controller="NotificationCtrl" ng-init="listNotification()">
		<table ng-show="notifications.length">
			<tr>
				<th>Notification</th>
				<th>Created At</th>
			</tr>
			<tr ng-repeat="notification in notifications">
				<td><a href="<c:url value="{{notification.url}}"/>">{{notification.message}}</a></td>
				<td>{{notification.createdAt}}</td>
			</tr>
		</table>
		<div ng-hide="notifications.length">No notification</div>
	</div>
	
	<!--
	
	<br>
	<br>
	Example
	<br>
	http://localhost:8080/moodflip/notification/create?message="Test message"&url="/forum"&userId=1
	
	-->

</body>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script src="<c:url value="/resources/comm/js/comm.js" />"></script>

</html>