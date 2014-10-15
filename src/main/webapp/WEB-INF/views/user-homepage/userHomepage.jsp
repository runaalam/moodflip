<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<jsp:include page="../fragments/headTag.jsp"/>
<body> 
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="container">
	<div class="row">
    	<!-- include Sidebar --> 	
	    <jsp:include page="../fragments/bodySideBar.jsp"/>
	    <div class="col-xs-12 col-sm-9">
			<form:form action="user-homepage" method="post" modelAttribute="status">
				<div class="well"><div class="form-group">
					<label for="statusInput">Status Update:</label>
					<form:textarea class="form-control" path="content" id="statusInput" rows="4" cols="50"></form:textarea>
					<input type="submit" value="Post"/>
				</div></div>
				<br><br><br>
				<div class="panel panel-default">
   					<div class="panel-heading"><h3 class="panel-title">My Activity</h3>
   					</div>
   					<div class="panel-body">
                    	<table class="table">	
	      					<c:choose>
	      						<c:when test="${!empty activityList}">
			                       <c:forEach items="${activityList}" var="activity">
			                       		<tr>
	                                     <%--    <td><c:out value="${userActivity.userId}" /></td>   --%>
	                                       <td><c:out value="${activity.description}" /> - </td>
	                                       <td><c:out value="${activity.activityDate}" /></td>                                        
			                               </tr>
			                       </c:forEach>
	     				 		</c:when>
       							<c:when test="${empty activityList}">There is no past status or activity</c:when>
			   				</c:choose>
			   			</table>
   					</div>
				</div>
			</form:form>
		</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>