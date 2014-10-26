<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
	<head>	
		<title><fmt:message key="userHomepage.status.details"/></title>
	</head>
	
	<body>
 		<div class="col-xs-12 col-sm-9">
        		<div class="panel panel-default">
                	<div class="panel-body">
               			<p>By <c:out value="${status.user.name}" />
               			<br><br><br>
                       	<c:out value="${status.content}" />
					</div>
				</div><!-- panel-default -->	
				
				<div class="panel panel-default">
					<form:form method="post" modelAttribute="statusComment">
	   					<div class="panel-heading"><label for="commentContent"><fmt:message key="userHomepage.status.comment"/></label>
							<form:textarea class="form-control" path="content" id="commentContent" rows="4" cols="50"></form:textarea>
							<input type="submit" value="Send"/>
	   					</div>
	   					<div class="bs-example">
	                    	<table class="table table-striped"><tbody>
		      					<c:choose>
		      						<c:when test="${!empty statusCommentList}">
				                       <c:forEach items="${statusCommentList}" var="statusComment">
				                       		<tr><td><c:out value="${statusComment.content}"/></td></tr>
				                       </c:forEach>
		     				 		</c:when>
	       							<c:when test="${empty statusCommentList}"><fmt:message key="userHomepage.status.comment.empty"/></c:when>
				   				</c:choose>
				   			</tbody></table>
	   					</div>
	   					</form:form>
				</div><!-- panel-default -->					
	    </div><!-- col-xs-12 main -->
	</body>
</html>