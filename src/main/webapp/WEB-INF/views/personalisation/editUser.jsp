<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Edit Account - <fmt:message key="title" /></title>
<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<!-- include page header -->
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<div class="container">
		<div class="row">
			<!-- include Sidebar -->
			<jsp:include page="../fragments/bodySideBar.jsp" />
			<div class="col-xs-12 col-sm-9">
				<h1>Account Settings</h1>
				<br />
				<form:form method="post" modelAttribute="user"
					class="form-horizontal" role="form">
					<fieldset>
						<div class="form-group">
							<form:label path="username" class="col-sm-2 control-label">Username</form:label>
							<div class="col-sm-5">
								<form:input path="username" disabled="true" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="name" class="col-sm-2 control-label">Name</form:label>
							<div class="col-sm-5">
								<form:input path="name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="password" class="col-sm-2 control-label">New Password</form:label>
							<div class="col-sm-5">
								<form:password path="password" class="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="userPrivacy" class="col-sm-2 control-label">Privacy
								Setting</label>
							<div class="col-sm-5">
								<sf:select path="privacy" class="form-control">
									<sf:option value="" label="--Please Select" />
									<sf:options items="${privacy}" itemLabel="text" />
								</sf:select>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<a href="<c:url value="/user/profile" />"><input
									type="button" value="Cancel" class="btn btn-default" /></a> <input
									type="submit" value="Done" class="btn btn-default" />
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
