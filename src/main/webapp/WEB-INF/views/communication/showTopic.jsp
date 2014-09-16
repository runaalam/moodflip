<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
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
	<c:out value="${topic.name}" />
	<br>
	<pre><c:out value="${topic.content}" /></pre>
	<br>
	Up Vote <c:out value="${topic.upVote}" /><a href="<c:url value="/forum/${forumId}/up_vote/${topic.id}"/>">+</a>
	<br>
	Down Vote <c:out value="${topic.downVote}" /><a href="<c:url value="/forum/${forumId}/down_vote/${topic.id}"/>">-</a>
</body>
</html>