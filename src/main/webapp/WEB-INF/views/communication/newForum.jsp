<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Forum - New Forum</title>
		<style>
			.error {
    			color: #ff0000;
    			font-style: italic;
    			font-weight: bold;
			}
		</style>
	</head>
	<body>
		<h1>New Forum</h1>
		<form:form modelAttribute="forum" method="post">
			Name: <form:input path="forumName"/>
			<form:errors path="forumName" cssClass="error" />
			<br>
			<br>
			<input type="submit" value="Create"/>
		</form:form>
	</body>
</html>