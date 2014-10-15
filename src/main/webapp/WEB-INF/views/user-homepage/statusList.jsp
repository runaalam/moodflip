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
	</div><!-- row -->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>