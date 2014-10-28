<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title" /> - Register</title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8">
				<h1>Register</h1>
			</div>
		</div>
		<br>
		<form action="register" method="post" class="form-horizontal"
			role="form">

			<div class="form-group">
				<c:if test="${not empty error}">
					<div class="col-sm-offset-3 col-sm-6 alert alert-danger"
						role="alert">${error}</div>
				</c:if>
			</div>

			<div class="form-group">
				<label class="col-sm-offset-1 col-sm-2 control-label">Username</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" placeholder="Username"
						name="username" autofocus />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-offset-1 col-sm-2 control-label">Password</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" placeholder="Password"
						name="password" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-offset-1 col-sm-2 control-label">Confirm
					Password</label>
				<div class="col-sm-6">
					<input type="password" class="form-control"
						placeholder="Confirm Password" name="password2" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-offset-1 col-sm-2 control-label">Name</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" placeholder="Name"
						name="name" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-6">
					<button type="submit" class="btn btn-block btn-primary btn-lg">Register</button>
				</div>
			</div>
		</form>

	</div>
	<%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
</html>