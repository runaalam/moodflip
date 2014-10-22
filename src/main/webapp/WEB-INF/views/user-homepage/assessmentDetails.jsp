<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


 <div class="col-xs-12 col-sm-9">
	<div class="panel panel-default">
	        <div class="panel-body">Recent Assessment</div>
	</div><!-- panel-default -->	
	<div class="panel panel-default">
		<div class="bs-example">
		<form:form modelAttribute="latestAssess">
			User: <c:out value="${latestAssess.userId}" />
			Score: <c:out value="${latestAssess.score}" />
		</form:form>
		</div>
	</div><!-- panel-default -->	
</div><!-- col-xs-12 main -->
	
