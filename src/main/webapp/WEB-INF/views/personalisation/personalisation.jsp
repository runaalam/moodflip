<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

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

DashBoard page
<h3>Users</h3>
		<table class="table table-bordered">
      <thead>
        <tr>
          <th>#</th>
          <th>Username</th>
          <th>Ban Status</th>
	<th></th>
	<th></th>
        </tr>
      </thead>
      
<c:forEach items="${users}" var="user">
<tbody>
        <tr>
          <td rowspan="2"><c:out value="${user.id}"/></td>
          <td><c:out value="${user.username}"/></td>
          <td><c:choose>
 	<c:when test="${user.banned}">
		Banned
	</c:when>
    	<c:otherwise>
		Active
    	</c:otherwise>
	</c:choose>
	</td>
        <td>    
		<c:choose>
   		<c:when test="${user.banned}">
			<a class="btn btn-default" href="<c:url value="/personalisation/unban/${user.id}"/>" role="button">unban</a>
    		</c:when>
    		<c:otherwise>
			<a class="btn btn-default" href="<c:url value="/personalisation/ban/${user.id}"/>" class=brole="button">ban</a>
    		</c:otherwise>
		</c:choose>
	</td>
       
        <td>
          	<a class="btn btn-default" href="<c:url value="/user/delete/${user.id}"/>">Delete</a>
        </td>
         </tr>
        </c:forEach>
      </tbody>
 </table>
 </div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>