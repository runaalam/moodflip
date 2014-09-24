<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User home page</title>
		<style>ul#menu {padding: 0;}
				ul#menu li {display: inline;}
				ul#menu li a {
				    background-color: green;
				    color: white;
				    padding: 10px 20px;
				    text-decoration: none;
				    border-radius: 4px 4px 0 0;}

				ul#menu li a:hover {background-color: orange;}
			#header {
				    background-color:#888888 ;
				    color:white;
				    text-align:center;
				    padding:5px;
				}
			#nav {
			    line-height:30px;
			    background-color:#eeeeee;
			    height:300px;
			    width:160px;
			    float:left;
			    padding:5px;	      
			}
			#section {
			    width:350px;
			    float:left;
			    padding:10px;	 	 
			}
			#footer {
			    background-color:black;
			    color:white;
			    clear:both;
			    text-align:center;
			   padding:5px;	 	 
			}
		</style>
	</head>
	<body> 
		<div id="header">
			<h1><fmt:message key="heading" /></h1> 
		</div>
		
		<!--  <h1>Home</h1>	-->
		
		<ul id="menu">
			<li><a href="<c:url value="/user-homepage"/>">Home</a></li>
 			<li><a href="<c:url value="/card-game"/>">Card Game</a></li>
			<li><a href="<c:url value="/mood-tracking"/>">Express Your Mood</a></li>
			<li><a href="<c:url value="/forum" />">Forum</a></li>
		</ul>  
		
	<!--  <table id=lTable style="float:left; width:160px; display: block; background-color: #dddddd;">
	        <tr><td><a href="<c:url value="/user-homepage/depression-assessment"/>">Depression Assessment</a></td></tr>
	        <tr><td><a href="<c:url value="/user-homepage/assessment-result"/>">Assessment Result</a></td></tr>
	        <tr><td><a href="<c:url value="/user-homepage/other-post"/>">Other's Status</a></td></tr>
	    	</table> -->
	    <div id="nav">
	    	<a href="<c:url value="/user-homepage/depression-assessment"/>">Depression Assessment</a><br>
	    	<a href="<c:url value="/user-homepage/assessment-result"/>">Assessment Result</a><br>
	    	<a href="<c:url value="/user-homepage/other-post"/>">Other's Status</a><br>
	    </div>
    
	   <!--   <form action="user-homepage" method="post">
			<table style="float:left; width:100px;">
				<tr><td>Status Update:<textarea name="content" rows="4" cols="50"></textarea></td></tr>
				<tr><td><input type="submit" value="Post"/>
				</table>
		</form> -->
		<div id="section">
			<form:form action="user-homepage" method="post" modelAttribute="status">
				<table style="float:left; width:100px;">
					<tr><td><label for="statusInput">Status Update:</label></td></tr>
					<tr><td><form:textarea path="content" id="statusInput" rows="4" cols="50"></form:textarea></td></tr>
					<tr><td><input type="submit" value="Post"/>
					<br><br><br>
					<h2>My Activity</h2>
					<c:choose>
       					<c:when test="${!empty statusList}">
               			<table>
               
	                       <c:forEach items="${statusList}" var="status">
	                               <tr>
	<%--                                         <td><c:out value="${status.userId}" /></td>   --%>
	                                       <td><c:out value="${status.content}" /> - </td>
	                                       <td><c:out value="${status.submitDate}" /></td>                                        
	                               </tr>
	                       </c:forEach>
               			</table>
      				 </c:when>
       				<c:when test="${empty statusList}">
				       There is no past status or activity
				       </c:when>
				       </c:choose>
				</table>
			</form:form>
		</div>	
	</body>
</html>