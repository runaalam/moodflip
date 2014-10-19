<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forums - New Topic</title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
</head>
<body>

<%@ include file="/WEB-INF/views/navbar.jsp"%>
	
	<div class="container">

<sec:authorize access="!isAuthenticated()">
				<li><a href="<c:url value="/login"/>">Login</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<li><a href=""><sec:authentication property="principal.username" /></a></li>
				<td><a href="<c:url value="/user/edit/${user.id}"/>">edit</a></td>
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
				</sec:authorize>
<security:authorize access="hasAnyRole('ADMIN')">
<h3>DashBoard</h3>
		<table border="1">
			<tr>
				<td>Username</td>
				<td>Banned Status</td>
			</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.username}"/></td>
				
				<td><c:out value="${user.banned}"/>
				<c:choose>
    <c:when test="${user.banned}}">
        Condition is true.
    </c:when>
    <c:otherwise>
        Condition is false.
    </c:otherwise>
</c:choose>
				</td>
				<td><a href="<c:url value="/user/edit/${user.id}"/>">edit</a></td>
				<td><a href="<c:url value="/user/delete/${user.id}"/>">Delete</a></td>
			</tr>
		</c:forEach>
		</table>
</security:authorize>
<a href="<c:url value = "/user/register"/>">register</a>
</body>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</html>