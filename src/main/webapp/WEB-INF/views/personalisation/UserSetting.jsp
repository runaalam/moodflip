<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Account Details</title>
	</head>
	<body>
		<h1><c:out value="${user.username}"/></h1>
		<table border="1">
		<tr>
		<td><c:out value="${user.name}"/></td>
		</tr>
		<tr>
		<td><c:out value="${user.privacy.text}"/></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/user/edit/${user.id}"/>">edit</a></td>
			<td><a href="<c:url value="/user/delete/${user.id}"/>">Delete</a></td>
		</tr>
		</table>
<a href="<c:url value = "/user/register"/>">register</a>
</body>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</html>