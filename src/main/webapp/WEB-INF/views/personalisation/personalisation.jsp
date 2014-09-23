
<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personalisation system</title>
</head>
<body>
Personalisation page
<h3>Users</h3>
		<table border="1">
			<tr>
				<td>Username</td>
				<td>Banned Status</td>
			</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.username}"/></td>
				
				<td><c:out value="${user.banned}"/></td>
				<td><a href="<c:url value="/user/edit/${user.id}"/>">edit</a></td>
				<td><a href="<c:url value="/user/delete/${user.id}"/>">Delete</a></td>
			</tr>
		</c:forEach>
		</table>
<a href="<c:url value = "/user/register"/>">register</a>
</body>
</html>