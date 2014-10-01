<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Edit Your Data</title>
</head>
<body>
	<form:form method="post" modelAttribute="data">
	<fieldset>
				<table>
					<tr>
					<th><form:label path="moodRating">Rate Your Mood:</form:label></th>
					<td><form:input path="moodRating" value="${editData.moodRating}"/></td>
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