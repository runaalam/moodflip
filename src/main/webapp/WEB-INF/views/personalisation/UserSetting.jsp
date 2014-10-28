<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Account Details - <fmt:message key="title" /></title>
</head>
<jsp:include page="../fragments/headTag.jsp" />
<body>
	<!-- include page header -->
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<div class="container">
		<div class="row">
			<!-- include Sidebar -->
			<jsp:include page="../fragments/bodySideBar.jsp" />
			<div class="col-xs-12 col-sm-9">
				<div class="row">
					<div class="col-xs-12">
						<h1>
							<c:out value="${user.username}" />
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<p>
							Name:
							<c:out value="${user.name}" />
						</p>
						<p>
							Privacy Settings:
							<c:out value="${user.privacy.text}" />
						</p>

						<a class="btn btn-default"
							href="<c:url value="/user/edit/${user.id}"/>">Edit</a> <a
							class="btn btn-danger"
							href="<c:url value="/user/delete/${user.id}"/>">Delete</a>
					</div>
				</div>
				<!-- 
		<c:forEach items="${friends}" var="friends">
			<tr>
			<c:out value="${friends.username}"/>
			</tr>
		</c:forEach>
		 -->

			</div>
			<!-- /.col-xs-12 main -->
		</div>
		<!--/.row-->
	</div>
	<!-- container -->
	<jsp:include page="../fragments/footer.jsp" />
</body>
<script src="<c:url value="/resources/card-game/js/edit.js" />"
	type="text/javascript"></script>
</html>
