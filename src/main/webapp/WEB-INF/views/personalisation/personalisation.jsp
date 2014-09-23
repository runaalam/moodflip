<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personalisation system</title>
</head>
<body>
Personalisation page
<h3>Users</h3>
		<c:forEach items="${users}" var="user">
		<table border="1">
			<tr>
				<td>Username</td>
				<td>Banned Status</td>
			</tr>
			<tr>
				<td><c:out value="${user.username}" /></td>
				
				<td><c:out value="${user.banned}" />
			</tr>
			
			
				
		</c:forEach>
<a href = "<c:url value = "/user/register"/>">register</a>
</body>
</html>