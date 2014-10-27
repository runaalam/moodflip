<%@page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
</head>

<body>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<div class="container">
	
		<div class="row">
			<div class="col-sm-offset-1 col-sm-10">
				<div class="jumbotron">
					<h1>Welcome to MoodFlip!</h1>
					<p class="lead">Please login to access the website.</p>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-offset-4 col-sm-4">
				<form id="form" action="<c:url value='/j_spring_security_check' />"
					method="POST" role="form">

					<div class="form-group">
						<c:if test="${not empty error}">
							<div class="alert alert-danger" role="alert">${error}</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="msg">${msg}</div>
						</c:if>
					</div>

					<div class="form-group">
						<label>Username</label> <input type="text" class="form-control"
							placeholder="Username" name="username" value="" autofocus />
					</div>

					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" placeholder="Password" name="password"
							value="">
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-block btn-primary btn-lg"
							name="submit">
							<abbr>Login</abbr>
						</button>
					</div>
				</form>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-offset-4 col-sm-4">
				<h4>
					Don't have an account yet? <a
						href="<c:url value="/user/register" />">Register here</a>.
				</h4>
			</div>
		</div>

	</div>

</body>

<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>

</html>