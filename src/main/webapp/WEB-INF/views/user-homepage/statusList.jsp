<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
	<head>
			<title><fmt:message key="userHomepage.status.post"/></title>	    
	</head>
<body> 

	    <div class="col-xs-12 col-sm-9">
	    	<div class="panel-group" id="accordion">
        		<div class="panel panel-default">
            		<div class="panel-heading">
                		<h3 class="panel-title"><fmt:message key="userHomepage.status.post.others"/></h3>
            		</div>
                	<div class="panel-body">
                   		<table class="table">
							<c:choose>
								<c:when test="${!empty statusList}">
									<form:form method="post" modelAttribute="statusList">
										<c:forEach items="${statusList}" var="status">
	                               			<tr><td>
                           						<h5><small><fmt:formatDate type="date" value="${status.submitDate}" /></small></h5>
	                               				<p><c:out value="${fn:substring(status.content, 0, 190)}" /> ....
	                                       		<a href="<c:url value="/user-homepage/other-post/statusId/${status.id}"/>">
		                                       		See more</a></p>
		                                     </td></tr>
	                               		<!--  <tr><td><c:out value="${status.content}" /> ....
	                                       		<a href="<c:url value="/user-homepage/other-post/statusId/${status.id}"/>">
		                                       		See more</a>
		                                       </td>
		                                       <td><c:out value="${status.submitDate}" /></td>                                        
	                              			 </tr> -->	
	                       				</c:forEach>
									</form:form>
								</c:when>
								<c:when test="${empty statusList}"><fmt:message key="userHomepage.status.post.others.empty"/></c:when>
							</c:choose>
						</table>
					</div>
				</div><!-- panel-default -->
			</div><!-- panel-group -->			
	    </div><!-- col-xs-12 main -->

</body>
</html>