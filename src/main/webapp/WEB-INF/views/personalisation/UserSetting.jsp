<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<jsp:include page="../fragments/headTag.jsp"/>
<body> 
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="container">
	<div class="row">
    	<!-- include Sidebar --> 	
	    <jsp:include page="../fragments/bodySideBar.jsp"/>
	    <div class="col-xs-12 col-sm-9">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Account Details</title>
	</head>
		<h1><c:out value="${user.username}"/></h1>
		<table border="1">
		<tr>
		<td><c:out value="${user.name}"/></td>
		</tr>
		<tr>
		<td><c:out value="${user.privacy.text}"/></td>
		</tr>
		<tr>
			<td><a class="btn btn-default" href="<c:url value="/user/edit/${user.id}"/>">edit</a></td>
			<td><a class="btn btn-default" href="<c:url value="/user/delete/${user.id}"/>">Delete</a></td>
		</tr>
		</table>
		<!--  
		<c:forEach items="${friends}" var="friends">
			<tr>
			<c:out value="${friends.username}"/>
			</tr>
		</c:forEach>-->
		
		
		
</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
	<script src="<c:url value="/resources/card-game/js/edit.js" />" type="text/javascript"></script>
</html>
