<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<body>
Personalisation page
<form action="register" method="post">
			Username: <input type="text" name="username"/>
			Password: <input type="password" name="password"/>
			PrivacySetting: <input type="text" name="privacySetting"/>
			name: <input type="text" name="name"/>
			<input type="submit" value="Add"/>
		</form>
<a href = "<c:url value = "/user/register"/>">submit</a>
</body>
</html>