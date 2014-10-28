<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html ng-app="moodFlip">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Dashboard - <fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</head>
<body>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<div class="container">

		<div class="row">
			<div class="col-md-12">
				<h2>Admin Dashboard</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h3>Users</h3>
			</div>
		</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Username</th>
					<th>Name</th>
					<th>Banned Status</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.name}" /></td>

						<td> <c:choose>
								<c:when test="${user.banned}">
								Banned
									<a class="btn btn-sm btn-default"
										href="<c:url value="/personalisation/unban/${user.id}"/>"
										role="button">Unban</a>
								</c:when>
								<c:otherwise>
								Active
									<a class="btn btn-sm btn-default"
										href="<c:url value="/personalisation/ban/${user.id}"/>"
										class=brole="button">Ban</a>
								</c:otherwise>
							</c:choose></td>
						<td><a class="btn btn-sm btn-default"
							href="<c:url value="/user/edit/${user.id}"/>">Edit</a> <a
							class="btn btn-sm btn-danger"
							href="<c:url value="/user/admin/delete/${user.id}"/>">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
</html>