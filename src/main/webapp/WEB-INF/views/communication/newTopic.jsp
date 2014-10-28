<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="moodFlip">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Topic - Forums - <fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/navbar.jsp"%>
	
	<div class="container">
	
	<h1>New Topic</h1>
	<form:form id="newTopicForm" modelAttribute="topic" method="post" class="form-horizontal" role="form">
	
		<s:bind path="name">
		<div class="form-group ${status.error ? 'has-error has-feedback' : '' }">
			<form:label path="name" class="col-sm-2 control-label">Name</form:label>
			<div class="col-sm-6">
				<form:input path="name" class="form-control" placeholder="Name" />
				<c:if test="${status.error}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
			</div>
			<form:errors path="name" class="col-sm-4 help-block" />
		</div>
		</s:bind>
		
		<s:bind path="content">
		<div class="form-group ${status.error ? 'has-error has-feedback' : '' }">
			<form:label path="content" class="col-sm-2 control-label">Content</form:label>
			<div class="col-sm-6">
				<form:textarea path="content" class="form-control" rows="10" placeholder="Content" />
				<c:if test="${status.error}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
			</div>
			<form:errors path="content" class="col-sm-4 help-block" />
		</div>
		</s:bind>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Post" class="btn btn-default" />
			</div>
		</div>

	</form:form>
	
	</div>
	<%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
</html>