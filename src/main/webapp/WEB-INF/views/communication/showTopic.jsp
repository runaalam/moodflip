<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>
		<a href="<c:url value="/forum" />">Forum</a> > <a
			href="<c:url value="/forum/${forumId}" />">${forumName}</a> >
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
	Up Vote <c:out value="${topic.upVote}" /><a href="<c:url value="/forum/${forumId}/up_vote/${topic.id}"/>">+</a>
	<br>
	Down Vote <c:out value="${topic.downVote}" /><a href="<c:url value="/forum/${forumId}/down_vote/${topic.id}"/>">-</a>
	
	<h2>Comments</h2>
	
	<hr>

	<c:forEach items="${comments}" var="comment">
		User: <c:out value="${comment.userId}" />
		<br>
		Created At: <c:out value="${comment.createdAt}" />
		<br>
		Edited At: <c:out value="${comment.editedAt}" />
		<br>
		<pre><c:out value="${comment.content}" /></pre>
		
		<br>
		<a href="<c:url value="/forum/${forumId}/edit/${topic.id}"/>">Edit</a>
		<a href="<c:url value="/forum/${forumId}/delete/${topic.id}"/>">Delete</a>
		
	<hr>
	
	</c:forEach>
	
	<h2>Reply</h2>
	<form:form id="replyForm" modelAttribute="comment" method="post">
		<table>
			<tr>
				<td><form:label path="content"></form:label></td>
				<td><form:textarea style="width: 300px; height: 100px;" path="content" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Post Reply" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>