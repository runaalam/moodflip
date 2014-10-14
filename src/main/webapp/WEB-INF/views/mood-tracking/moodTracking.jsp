<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Express Your Mood</title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
</head>
<body>
		<%@ include file="/WEB-INF/views/navbar.jsp"%>
<h1>
		<fmt:message key="heading" />
		<div class="container">
		
		
	</h1>

	<h3><a href="<c:url value="/data"/>">Data</a></h3>
	

	<h3><a href="<c:url value="/charts"/>">Charts</a></h3>
	

	<h3><a href="<c:url value="/reports"/>">Reports</a></h3>
	
	
</body>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</html>
