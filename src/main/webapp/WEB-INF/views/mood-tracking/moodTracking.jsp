<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Express Your Mood</title>
</head>
<body>

<h1>
		<fmt:message key="heading" />
	</h1>

	<h3><a href="<c:url value="/data"/>">Data</a></h3>

	<h3><a href="<c:url value="/charts"/>">Charts</a></h3>
	

	<h3><a href="<c:url value="/reports"/>">Reports</a></h3>
	
	
</body>
</html>
