<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Edit Topic</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
	</head>
	<body>
		<h1>Edit Topic</h1>
		<form:form method="post" modelAttribute="topic">
			<fieldset>
				<table>
					<tr>
						<th><form:label path="name">Name:</form:label></th>
						<td><form:input path="name" /></td>
						<td><form:errors path="name" cssClass="error" /></td>
					</tr>
					<tr>
						<th><form:label path="content">Content:</form:label></th>
						<td><form:textarea path="content" /></td>
						<td><form:errors path="content" cssClass="error" /></td>
					</tr>
					<tr>
						<td><input type="button" value="Cancel" onclick="window.location='../'" /></td>
						<!-- This hidden field is required for Hibernate to recognize this Product -->
						<!-- <form:hidden path="createdAt" /> -->
						<td><input type="submit" value="Done"/></td>
					</tr>
				</table>			
			</fieldset>
		</form:form>
	</body>
</html>