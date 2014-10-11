<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title" /></title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>
		<a href="<c:url value="/forum" />">Forum</a> > <a
			href="<c:url value="/forum/${topic.forum.id}" />">${topic.forum.forumName}</a> >
		${topic.name}
	</h3>
	<h2><c:out value="${topic.name}" /></h2>
	<br>
	User: <c:out value="${topic.userId}" />
	<br>
	Created At: <c:out value="${topic.createdAt}" />
	<br>
	Edited At: <c:out value="${topic.editedAt}" />
	<br>
	<pre><c:out value="${topic.content}" /></pre>
	<br>
	<a href="<c:url value="/forum/${topic.forum.id}/topic/edit/${topic.id}"/>">Edit</a>
	<a href="<c:url value="/forum/${topic.forum.id}/topic/delete/${topic.id}"/>">Delete</a>
	<br>
	Up Vote <c:out value="${topic.upVote}" /><a href="<c:url value="/forum/${topic.forum.id}/topic/up_vote/${topic.id}"/>">+</a>
	<br>
	Down Vote <c:out value="${topic.downVote}" /><a href="<c:url value="/forum/${topic.forum.id}/topic/down_vote/${topic.id}"/>">-</a>
	
	<h2>Comments</h2>
	
	<hr>

	<c:choose>
	<c:when test="${!empty comments}">
	<c:forEach items="${comments}" var="comment">
		User: <c:out value="${comment.userId}" />
		<br>
		Created At: <c:out value="${comment.createdAt}" />
		<br>
		Edited At: <c:out value="${comment.editedAt}" />
		<br>
		<pre><c:out value="${comment.content}" /></pre>
		
		<br>
		<a href="<c:url value="/forum/${topic.forum.id}/topic/${topic.id}/comment/edit/${comment.id}"/>">Edit</a>
		<a href="<c:url value="/forum/${topic.forum.id}/topic/${topic.id}/comment/delete/${comment.id}"/>">Delete</a>
		<br>
		Up Vote <c:out value="${comment.upVote}" /><a href="<c:url value="/forum/${forum.id}/topic/${topic.id}/comment/up_vote/${comment.id}"/>">+</a>
		<br>
		Down Vote <c:out value="${comment.downVote}" /><a href="<c:url value="/forum/${forum.id}/topic/${topic.id}/comment/down_vote/${comment.id}"/>">-</a>
		
	<hr>
	
	</c:forEach>
	</c:when>
	<c:when test="${empty comments}">
	There is no comment.
	</c:when>
	</c:choose>
	
	<br>
	<a href="<c:url value="/forum/${topic.forum.id}/topic/${topic.id}/comment/create"/>">Reply</a>
	<br>

</body>
</html>