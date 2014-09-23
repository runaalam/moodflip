<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>Questions</h3>
	<c:choose>
		<c:when test="${!empty quesList}">
			<table border="1">
				<tr>
					<th>Assessment Questions</th>
				</tr>
				<c:forEach items="${quesList}" var="question">
					<tr>
						<td>${question.id}</td>
						<td>${question.text}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		
		<c:when test="${empty quesList}">
			There is no questions.
		</c:when>
	</c:choose>


</body>
</html>