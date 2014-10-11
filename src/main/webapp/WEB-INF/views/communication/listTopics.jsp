<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3><a href="<c:url value="/forum" />">Forum</a> > ${forum.forumName}</h3>

	<c:url value="/forum/${forum.id}" var="pagedLink">
		<c:param name="p" value="~" />
	</c:url>

	<c:choose>
	<c:when test="${!empty pagedListHolder.pageList}">
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
			<c:forEach items="${pagedListHolder.pageList}" var="topic">
				<tr>
					<td><a href="<c:url value="/forum/topic/${topic.id}"/>"><c:out value="${topic.name}" /></a></td>
					<td><c:out value="${topic.userId}" /></td>
					<td><c:out value="${topic.upVote}" /></td>
					<td><c:out value="${topic.downVote}" /></td>
					<td><c:out value="${topic.pinned}" /></td>
					<td><c:out value="${topic.createdAt}" /></td>
					<td><c:out value="${topic.editedAt}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:when test="${empty pagedListHolder.pageList}">
	There is no topic.
	</c:when>
	</c:choose>
	
	<br>
	<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
	<br>

	<br>
	<a href="<c:url value="/forum/${forum.id}/newTopic"/>">Create Topic</a>
	<br>

</body>
</html>