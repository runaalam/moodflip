<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
	<head>	
		<title>Status Details</title>
	</head>
	
	<body>
 		<div class="col-xs-12 col-sm-9">
        		<div class="panel panel-default">
                	<div class="panel-body">
               			<strong><c:out value="${status.user.name}"/></strong>
               			<h5><small><fmt:formatDate type="date" value="${status.submitDate}" /></small></h5>
               			<hr>
                       	<c:out value="${status.content}" />
					</div>
				</div><!-- panel-default -->	
				
				<div class="panel panel-default">
					<form:form method="post" modelAttribute="statusComment">
	   					<div class="panel-heading"><label for="commentContent">Comment</label>
							<form:textarea class="form-control" path="content" id="commentContent" rows="4" cols="50"></form:textarea>
							<br><p align="right"><input class="btn btn-primary" type="submit"  value="Send"/></p>
							<hr>
	   					</div>
<%-- 	   					<form:errors path="content" cssStyle="color: red;"/>					--%>
	   					<div class="panel-body">
		      					<c:choose>
		      						<c:when test="${!empty statusCommentList}">
				                       <c:forEach items="${statusCommentList}" var="comment" varStatus="sc">
				                       		<c:set var="i" value="${sc.index}"/>
					                       <strong><c:out value="${commentUserList[i].name}"/></strong>
 					                       <h5><small><fmt:formatDate type="date" value="${comment.commentDate}" /></small></h5>
 				                       			<c:out value="${comment.content}"/>
				                       		<hr>
				                       </c:forEach>
		     				 		</c:when>
	       							<c:when test="${empty statusCommentList}">There are no comments for this post</c:when>
				   				</c:choose>
	   					</div>
	   					</form:form>
				</div><!-- panel-default -->					
	    </div><!-- col-xs-12 main -->
	</body>
</html>