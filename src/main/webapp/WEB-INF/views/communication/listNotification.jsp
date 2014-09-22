<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>Notification</h3>
	<c:choose>
	<c:when test="${!empty notifications}">
		<table border="1">
			<tr>
				<th>Notification</th>
				<th>User ID</th>
			</tr>
			<c:forEach items="${notifications}" var="notification">
				<tr>
					<td><a href="<c:url value="${notification.url}"/>"><c:out
								value="${notification.message}" /></a></td>
					<td><c:out value="${notification.userId}" /></td>
					<td><a href="<c:url value="/forum/delete/${forum.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:when test="${empty notifications}">
	There is no notification.
	</c:when>
	</c:choose>

</body>
</html>