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
        		<div class="panel panel-default">
                	<div class="panel-body">
               			<p>By <c:out value="${status.user.name}" />
               			<br><br><br>
                       	<c:out value="${status.content}" />
					</div>
				</div><!-- panel-default -->	
				
				<div class="panel panel-default">
					<form:form method="post" modelAttribute="statusComment">
	   					<div class="panel-heading"><label for="commentContent">Comment</label>
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
	       							<c:when test="${empty statusCommentList}">There is no past comment for this post</c:when>
				   				</c:choose>
				   			</tbody></table>
	   					</div>
	   					</form:form>
				</div><!-- panel-default -->	
				
	    </div><!-- col-xs-12 main -->
	</div><!-- row -->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>