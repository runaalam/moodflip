<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html ng-app="moodFlip">
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>Private Message</h3>
	
	<div ng-controller="PrivateMessageCtrl" ng-init="setReceiverId(<c:out value="${receiverId}" />)">
	
	<c:choose>
	<c:when test="${!empty users}">
		<table border="1">
			<tr>
				<th>User</th>
			</tr>
			<c:forEach items="${users}" var="user">
			<c:if test="${user.username != currentUser}">
				<tr>
					<td><a href="#" ng-click="setReceiverId(${user.id})"><c:out value="${user.username}" /></a></td>
				</tr>
			</c:if>
			</c:forEach>
		</table>
	</c:when>
	<c:when test="${empty users}">
	There is no user.
	</c:when>
	</c:choose>
	
	<hr>
	
	<div ng-repeat="message in messages">
		{{message.senderId}} : {{message.content}} ({{message.createdAt}})
		<br>
	</div>

		<form ng-submit="send()" ng-show="userSelected">
			<input type="text" ng-model="message" name="message" />
			<input type="submit" id="send" value="Send" />
		</form>


	</div>



</body>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-sanitize.min.js"></script>
<script src="<c:url value="/resources/comm/js/main.js" />"></script>
<script src="<c:url value="/resources/comm/js/pm.js" />"></script>

</html>