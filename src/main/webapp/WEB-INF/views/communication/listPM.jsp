<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>Private Message</h3>
	<c:choose>
	<c:when test="${!empty privateMessages}">
		<table border="1">
			<tr>
				<th>Content</th>
				<th>Sender ID</th>
				<th>Receiver ID</th>
				<th>Created</th>
			</tr>
			<c:forEach items="${privateMessages}" var="pm">
				<tr>
					<td><c:out value="${pm.content}" /></td>
					<td><c:out value="${pm.senderId}" /></td>
					<td><c:out value="${pm.receiverId}" /></td>
					<td><c:out value="${pm.createdAt}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:when test="${empty privateMessages}">
	There is no private message.
	</c:when>
	</c:choose>

	<br>
	<form:form id="createPM_test" modelAttribute="privateMessage" method="post">
		<table>
			<tr>
				<td><form:label path="content">Message content</form:label></td>
				<td><form:textarea style="width: 300px; height: 100px;" path="content" /></td>
				<td><form:errors path="content" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="senderId">Sender ID</form:label></td>
				<td><form:input path="senderId" /></td>
				<td><form:errors path="senderId" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="receiverId">Receiver ID</form:label></td>
				<td><form:input path="receiverId" /></td>
				<td><form:errors path="receiverId" cssClass="error" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Create" /></td>
			</tr>
		</table>
	</form:form>
	<br>

</body>
</html>