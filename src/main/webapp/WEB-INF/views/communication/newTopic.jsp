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
	
	<a href="<c:url value="/forums/${forumId}" />">&laquo; Back to Forum</a>
	
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
				<form:textarea path="content" class="form-control" rows="12" placeholder="Content" ng-model="content" />
				<c:if test="${status.error}"><span class="glyphicon glyphicon-remove form-control-feedback"></span></c:if>
			</div>
			<form:errors path="content" class="col-sm-4 help-block" />
		</div>
		</s:bind>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-6">
				<p class="text-muted">Supports HTML tags and Markdown syntax.
				<input type="button" value="Preview" class="btn btn-default pull-right" data-toggle="modal" data-target="#preview" />
				</p>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Post" class="btn btn-primary" />
			</div>
		</div>

	</form:form>

		<!-- Preview Modal -->
		<div class="modal fade" id="preview" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Content Preview</h4>
					</div>
					<div class="modal-body">
						<p ng-bind-html="content | markdown"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
<script src="//cdnjs.cloudflare.com/ajax/libs/showdown/0.3.1/showdown.min.js"></script>
</html>