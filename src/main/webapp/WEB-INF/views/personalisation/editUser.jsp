<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Edit Forum</title>
	</head>
	<body>
		<h1>Edit Forum</h1>
		<form:form method="post" modelAttribute="user">
			<fieldset>
				<table>
					<tr>
						<th><form:label path="name">Name:</form:label></th>
						<td><form:input path="name" value="${user.name}"/></td>
					</tr>
					<tr>
						<th><form:label path="password">Password:</form:label></th>
						<td><form:password path="password" value="${user.password}"/></td>
					</tr>
					<tr>
						<td><input type="button" value="Cancel" onclick="window.location='../'" /></td>
						<!-- This hidden field is required for Hibernate to recognize this Product -->
						<!-- <td><form:hidden path="id"/> -->
						<td><input type="submit" value="Done"/></td>
					</tr>
				</table>			
			</fieldset>
		</form:form>
	</body>
</html>