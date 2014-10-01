<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>

	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>Data</h3>
	
	<c:forEach items="${data}" var="data">
				<tr>
					<td><a href="<c:url value="/data/${data.id}"/>"><c:out
								value="${data.moodRting}" /></a></td>
					<td><a href="<c:url value="/data/edit/${data.id}"/>">Edit</a>
						
				</tr>
			</c:forEach>

</body>
</html>