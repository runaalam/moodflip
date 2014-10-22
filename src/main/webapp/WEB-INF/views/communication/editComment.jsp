<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Edit Comment</title>
		<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
	</head>
	<body>
		<%@ include file="/WEB-INF/views/navbar.jsp"%>
		
		<div class="container">
		
		<a href="<c:url value="/forums/topic/${topicId}" />">&laquo; Back to Topic</a>
		
		<h1>Edit Comment</h1>
		<form:form method="post" modelAttribute="comment" class="form-horizontal" role="form">

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
					<a href="<c:url value="/forums/topic/${topicId}" />"><input type="button" value="Cancel" class="btn btn-default" /></a>
					<input type="submit" value="Done" class="btn btn-default" />
				</div>
			</div>

			<!-- This hidden field is required for Hibernate to recognize this Comment -->
			<form:hidden path="id"/>
			
		</form:form>
		</div>
	</body>
</html>