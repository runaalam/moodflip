<%@ include file="/WEB-INF/views/include.jsp"%>
<html ng-app="moodFlip">
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>Forum</h3>
	<c:choose>
	<c:when test="${!empty forums}">
		<table border="1">
			<tr>
				<th>Forum</th>
			</tr>
			<c:forEach items="${forums}" var="forum">
				<tr>
					<td><a href="<c:url value="/forum/${forum.id}"/>"><c:out
								value="${forum.forumName}" /></a></td>
					<!--<td><a href="<c:url value="/forum/edit/${forum.id}"/>">Edit</a>
						<a href="<c:url value="/forum/delete/${forum.id}"/>">Delete</a></td>-->
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:when test="${empty forums}">
	There is no forum.
	</c:when>
	</c:choose>

	<br>
	<a href="<c:url value="/forum/create"/>">Create Forum</a>
	<br>



	<div ng-controller="ForumCtrl" ng-init="listForum()">
		<table>
			<tr ng-repeat="forum in forums">
				<td><a href="<c:url value="/forum/{{forum.id}}"/>">{{forum.forumName}}</a></td>
			</tr>
		</table>
	</div>



</body>


<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script src="<c:url value="/resources/comm/js/comm.js" />"></script>

</html>