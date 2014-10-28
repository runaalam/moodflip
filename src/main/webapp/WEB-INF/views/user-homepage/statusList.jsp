<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
	<head>
			<title>Status Posts</title>	    
	</head>
<body> 

	    <div class="col-xs-12 col-sm-9">
	    	<div class="panel-group" id="accordion">
        		<div class="panel panel-default">
            		<div class="panel-heading">
                		<h3 class="panel-title">Status post by other users</h3>
            		</div>
                	<div class="panel-body">
                   		<table class="table">
							<c:choose>
								<c:when test="${!empty otherStatusList}">
									<form:form method="get" modelAttribute="otherStatusList">
										<c:forEach items="${otherStatusList}" var="status"  varStatus="s">
	                               			<tr><td><c:set var="i" value="${s.index}"/>
					                       		<strong><c:out value="${userList[i].name}"/></strong>
                           						<h5><small><fmt:formatDate type="date" value="${status.submitDate}" /></small></h5>
	                               				<p><c:out value="${fn:substring(status.content, 0, 190)}" /> ....
	                                       		<a href="<c:url value="/user-homepage/other-post/statusId/${status.id}"/>">
		                                       		See more</a></p>
		                                     </td></tr>
	                       				</c:forEach>
									</form:form>
								</c:when>
								<c:when test="${empty statusList}">There are no posts</c:when>
							</c:choose>
						</table>
					</div>
				</div><!-- panel-default -->
			</div><!-- panel-group -->			
	    </div><!-- col-xs-12 main -->

</body>
</html>