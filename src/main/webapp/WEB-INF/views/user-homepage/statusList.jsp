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
                		<h3 class="panel-title"> Status post by other user</h3>
            		</div>
                	<div class="panel-body">
                   		<table class="table">
							<c:choose>
								<c:when test="${!empty statusList}">
									<form:form method="post" modelAttribute="statusList">
										<c:forEach items="${statusList}" var="status">
	                               			<tr><td><c:out value="${status.content}" /> ....
	                                       		<a href="<c:url value="/user-homepage/other-post/statusId/${status.id}"/>">
		                                       		Read more</a>
		                                       </td>
		                                       <td><c:out value="${status.submitDate}" /></td>                                        
	                              			 </tr>
	                       				</c:forEach>
									</form:form>
								</c:when>
								<c:when test="${empty statusList}">There is no post.</c:when>
							</c:choose>
						</table>
					</div>
				</div><!-- panel-default -->
			</div><!-- panel-group -->			
	    </div><!-- col-xs-12 main -->

</body>
</html>