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
	<h1>New Topic</h1>
	<form:form id="newTopicForm" modelAttribute="topic" method="post">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="content">Content</form:label></td>
				<td><form:textarea style="width: 300px; height: 100px;" path="content" /></td>
				<td><form:errors path="content" cssClass="error" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Post" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>