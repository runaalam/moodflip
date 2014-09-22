<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Custom cards</title>
</head>
<body>
<a href="<c:url value="/card-game/customCards?new"/>">Create new card</a>
<!-- <sf:form method="POST" modelAttribute=""> -->
	<table>
		<thead>
			<tr>
				<th><form:checkbox path="" value=""/></th>
				<th>Title</th>
				<th>Level</th>
				<th>Symptom</th>
				<th>Average Rating</th>
				<th>Completions</th>
				<th>Attempts</th>
				<th>Id</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${model.customCards}" var="card">
				<tr>
					<td><a href="<c:url value="/card-game/customCards?delete=${card.cardId}"/>">Delete</td>
					<td><a href="<c:url value="/card-game/customCards?edit=${card.cardId}"/>"><c:out value="${card.title}"/></a></td>
					<td><c:out value="${card.level}"/></td>
					<td><c:out value="${card.symptom.text}"/></td>
					<td><c:out value="${card.avgRating}"/></td>
					<td><c:out value="${card.completions}"/></td>
					<td><c:out value="${card.attempts}"/></td>
					<td><c:out value="${card.cardId}"/></td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
<!-- </sf:form> -->

</body>
</html>