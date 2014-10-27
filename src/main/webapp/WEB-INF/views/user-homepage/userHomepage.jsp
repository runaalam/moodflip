<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
	<head>
			<title><fmt:message key="userHomepage.title"/></title>	    	
	</head>
	<body> 
	    <div class="col-xs-12 col-sm-9">
			<form:form method="post" modelAttribute="status">
				<div class="well"><div class="form-group">
					<label for="statusInput">Status Update:</label>
					<c:if test="${myStatusNew == true}">
						<form:textarea class="form-control" path="content" id="statusInput" rows="4" cols="50"></form:textarea>
					</c:if>
					<c:if test="${myStatusNew == false}">
						<form:textarea class="form-control" path="content" id="statusInput" value="${status.content}" rows="4" cols="50"></form:textarea>
					</c:if>
					<input type="submit" value="Post"/>
				</div></div>
				<br><br><br>
				<div class="panel panel-default">
					<div class="panel-heading"><h3 class="panel-title"><fmt:message key="userHomepage.status.my"/></h3></div>
  					<div class="bs-example">
						<table class="table">
							<c:choose>
								<c:when test="${!empty statusList}">
									<form:form method="post" modelAttribute="statusList">
										<c:forEach items="${statusList}" var="status">
	                               			<tr><td><c:out value="${status.content}" /> ....
	                                       			<a href="<c:url value="/user-homepage/my-status/statusId/${status.id}"/>">
		                                       		Read more</a></td>
		                                        <td><c:out value="${status.submitDate}" /></td>  
		                                        <td><a href="<c:url value="/user-homepage/edit/${status.id}"/>"> Edit </a> | 
               										<a href="<c:url value="/user-homepage?delete=${status.id}"/>"> Delete</a> 
               									</td>    
	                              			 </tr>
	                       				</c:forEach>
									</form:form>
								</c:when>
								<c:when test="${empty statusList}"><fmt:message key="userHomepage.status.post.others.empty"/></c:when>
							</c:choose>
						</table>
					</div>
				</div>
				<div class="panel panel-default">
  					<div class="panel-heading"><h3 class="panel-title"><fmt:message key="userHomepage.activity.my"/></h3></div>
  						<div class="bs-example">
	                    <table class="table table-striped"><tbody>
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
	      							<c:when test="${empty activityList}"><fmt:message key="userHomepage.activity.empty"/></c:when>
			   				</c:choose>
			   			</table>
  					</div>
				</div><!-- panel-default -->
			</form:form>
			
			<iframe width="420" height="345"
src="http://youtu.be/QThB769iwxY">
</iframe>
		</div><!-- /.col-xs-12 main -->	
	</body>
</html>