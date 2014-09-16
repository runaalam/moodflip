<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3><a href="<c:url value="/forum" />">Forum</a> > ${forumName}</h3>
	<c:if test="${!empty topics}">
		<table border="1">
			<tr>
				<th>Topic</th>
				<th>User ID</th>
				<th>Up Vote</th>
				<th>Down Vote</th>
				<th>Pinned</th>
				<th>Created</th>
				<th>Edited</th>
			</tr>
			<c:forEach items="${topics}" var="topic">
				<tr>
					<td><a href="<c:url value="/forum/${forumId}/${topic.id}"/>"><c:out value="${topic.name}" /></a></td>
					<td><c:out value="${topic.userId}" /></td>
					<td><c:out value="${topic.upVote}" /></td>
					<td><c:out value="${topic.downVote}" /></td>
					<td><c:out value="${topic.pinned}" /></td>
					<td><c:out value="${topic.createdAt}" /></td>
					<td><c:out value="${topic.editedAt}" /></td>
					<td><a
						href="<c:url value="/forum/${forumId}/edit/${topic.id}"/>">Edit</a>
						<a href="<c:url value="/forum/${forumId}/delete/${topic.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<br>
	<a href="<c:url value="/forum/${forumId}/create"/>">Create Topic</a>
	<br>

</body>
</html>