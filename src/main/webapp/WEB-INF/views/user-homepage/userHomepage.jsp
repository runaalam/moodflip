<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
	<head>
			<title>Home</title>	
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
				</div>
					<p align="right"><input class="btn btn-primary" type="submit" value="Post"/></p>
				</div>
 				<form:errors path="content" cssStyle="color: red;"/>									
				<br><br><br>
				<div class="panel panel-default">
					<div class="panel-heading"><h3 class="panel-title">My status</h3></div>
  					<div class="panel-body">
					 <table class="table">  
							<c:choose>
								<c:when test="${!empty statusList}">
									<form:form method="post" modelAttribute="statusList">
										<c:forEach items="${statusList}" var="status">
	                               			<tr><td>
                           						<h5><small><fmt:formatDate type="date" value="${status.submitDate}"/></small>
             										&nbsp;&nbsp;
	             									<a style="PADDING-RIGHT" href="<c:url value="/user-homepage/edit/${status.id}"/>"> Edit</a> | 
		                               				<a href="<c:url value="/user-homepage?delete=${status.id}"/>"> Delete</a>
		                               			</h5>
                           						<p><c:out value="${fn:substring(status.content, 0, 190)}" /> ....
                                   				<a href="<c:url value="/user-homepage/my-status/statusId/${status.id}"/>">See post</a></p>
		                                    </td></tr>
	                       				</c:forEach>
									</form:form>
								</c:when>
								<c:when test="${empty statusList}">There are no posts</c:when>
							</c:choose>
						</table>  
					</div>
				</div>
				<div class="panel panel-default">
  					<div class="panel-heading"><h3 class="panel-title">My Activity</h3></div>
	                    <table class="table table-striped"><tbody>
	      					<c:choose>
	      						<c:when test="${!empty activityList}">
			                       <c:forEach items="${activityList}" var="activity">
			                       		<tr><td><h5>
                                       		<c:out value="${activity.description}"/>&nbsp;&nbsp; 
                                       		<small><fmt:formatDate type="date" value="${activity.activityDate}" /></small>
	                                    </h5></td></tr>
			                       </c:forEach>
	     				 		</c:when>
	      						<c:when test="${empty activityList}"><br/>&nbsp;&nbsp;&nbsp;There are no past activities<br/><br/><br/></c:when>
			   				</c:choose>
			   			</table>
					</div><!-- panel-default -->
				</form:form>
			</div><!-- /.col-xs-12 main -->	
</body>
</html>