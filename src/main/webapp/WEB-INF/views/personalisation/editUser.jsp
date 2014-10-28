<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<jsp:include page="../fragments/headTag.jsp"/>
<body> 
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp"/>

    	<!-- include Sidebar --> 	
	    <jsp:include page="../fragments/bodySideBar.jsp"/>
	    <div class="col-xs-12 col-sm-9">
			<h1>Account Settings</h1><br/>
		<form:form method="post" modelAttribute="user">
			<fieldset>
				<table>
					<tr>
						<th><form:label path="username">Username:</form:label></th>
						<td><form:input path="username" disabled="true" /></td>
					</tr>
					<tr>
						<th><form:label path="name">Name:</form:label></th>
						<td><form:input path="name" value="${user.name}"/></td>
					</tr>
					<tr>
						<th><form:label path="password">Password:</form:label></th>
						<td><form:password path="password" /></td>
					</tr>
					<tr>
						<td><a href="/personalisation.htm"><button>Cancel</button></a></a></td>
						<!-- This hidden field is required for Hibernate to recognize this Product -->
						<!-- <td><form:hidden path="id"/> -->
					</tr>
				</table>
				<!-- 
				<div class="form-group">	
					<label for="userPrivacy" class="col-sm-2 control-label">Privacy Setting</label>
					<div class="col-sm-5">
						<sf:select path="privacy" class="form-control">
							<sf:option value="" label="--Please Select"/>
							<sf:options items="${privacy}" itemLabel="text"/>
						</sf:select>
					</div>
				</div>	
				-->
				<td><input type="submit" value="Done"/></td>			
			</fieldset>
		</form:form>
		</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
	<script src="<c:url value="/resources/card-game/js/edit.js" />" type="text/javascript"></script>
</html>
