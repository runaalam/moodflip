<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title>Reports</title>
</head>
<body>
	

	<c:if test="${!empty data}">
		<table border="1">
			<tr>
				<th>Data</th>
			</tr>
			<c:forEach items="${data}" var="data">
				<tr>
					<td><a href="<c:url value="/reports/${data.id}"/>"><c:out
								value="${moodRating}" /></a></td>
					<td><a href="<c:url value="/forum/edit/${forum.id}"/>">Edit</a>
						<a href="<c:url value="/forum/delete/${forum.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<br>
	<a href="<c:url value="/forum/create"/>">Create Forum</a>
	<br>

</body>
</html>