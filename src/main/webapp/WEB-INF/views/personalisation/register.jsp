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
	
<h1>Personalisation page</h1>
<form action="register" method="post" class="form-horizontal" role="form">





			<br>Username: <input type="text" name="username"/>
			<br>Password: <input type="password" name="password"/>
			<br>Re-type Password: <input type="password2" name="password2"/>
			<br>PrivacySetting: <input type="text" name="privacySetting"/>
			<br>name: <input type="text" name="name"/>
			<input type="submit" value="Add"/>
		</form>
<a href = "<c:url value = "/user/register"/>">submit</a>

</body>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</html>