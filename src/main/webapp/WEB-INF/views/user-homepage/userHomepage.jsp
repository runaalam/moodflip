<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User home page</title>
	</head>
	<body>  
		<h1>Home</h1>
		<h3><a href="<c:url value="/user-homepage/depression-assessment"/>">Depression Assessment</a></h3>
		<h3><a href="<c:url value="/user-homepage/assessment-result"/>">Survey Result</a></h3>
		<h3><a href="<c:url value="/user-homepage/audio-video"/>">Audio/Video</a></h3>
		<h3><a href="<c:url value="/user-homepage/other-post"/>">Other Post</a></h3>
		<form action="/user-homepage" method="post">
			Status update: <input type="text" name="statusUpdate"/>
			<input type="submit" value="Post"/>
		</form>
	</body>
</html>