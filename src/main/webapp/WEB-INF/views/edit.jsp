<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit an old product on this page</title>
	</head>
	<body>
		<h1>You can edit the product shown below</h1>
		<sf:form method="POST" modelAttribute="product">
			<fieldset>
				<table>
					<tr>
						<th><label for="product_description">Description:</label></th>
						<td><sf:input path="description"/></td>
					</tr>
					<tr>
						<th><label for="product_price">Price:</label></th>
						<td><sf:input path="price"/></td>
					</tr>
					<tr>
						<th><a href="hello.htm"><button>Cancel</button></a></th>
						<!--  This is hidden field required for Hibernate to recognise this Product -->
						<td><sf:hidden path="id"/>
						<td><input type="submit" value="Done"/></td>
					</tr>	
				</table>
			</fieldset>
		</sf:form>
	</body>
</html>