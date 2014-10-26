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
	

DashBoard page
<h3>Users</h3>
		<table border="1">
			<tr>
				<td>Username</td>
				<td>Banned Status</td>
			</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.username}"/></td>
				
				<td><c:out value="${user.banned}"/>
				<c:if test="${user.banned}">button on</c:if>
    <c:choose>
   <c:when test="${user.banned}">
        <button type="button" class="btn btn-default" ng-click="ban(<c:out value="${user.id}"/>)">
				</button>
				<a href="<c:url value="/personalisation/unban/${user.id}"/>" role="button">unban</a>
    </c:when>
    <c:otherwise>
       <button type="button" class="btn btn-default" ng-click="upban(<c:out value="${user.id}"/>)">
 				
				</button>
				<a class="btn btn-default" href="<c:url value="/personalisation/ban/${user.id}"/>" class=brole="button">ban</a>
    </c:otherwise>
</c:choose></td>
				<td><a href="<c:url value="/user/edit/${user.id}"/>">edit</a></td>
				<td><a href="<c:url value="/user/delete/${user.id}"/>">Delete</a></td>
			</tr>
		</c:forEach>
		</table>
<a href="<c:url value = "/user/register"/>">register</a>
</body>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</html>