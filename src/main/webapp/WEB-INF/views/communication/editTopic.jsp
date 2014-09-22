<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Edit Topic</title>
	</head>
	<body>
		<h1>Edit Topic</h1>
		<form:form method="post" modelAttribute="topic">
			<fieldset>
				<table>
					<tr>
						<th><form:label path="name">Name:</form:label></th>
						<td><form:input path="name" value="${topic.name}"/></td>
					</tr>
					<tr>
						<th><form:label path="content">Content:</form:label></th>
						<td><form:textarea path="content" value="${topic.content}"/></td>
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