<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User home page</title>
		<style>ul#menu li {display:inline;}</style>
	</head>
	<body>  
		<h1>Home</h1>	
					
		<table style="float:left; width:200px;">
	        <tr><td><a href="<c:url value="/user-homepage/depression-assessment"/>">Depression Assessment</a></td></tr>
	        <tr><td><a href="<c:url value="/user-homepage/assessment-result"/>">Assessment Result</a></td></tr>
	        <tr><td><a href="<c:url value="/user-homepage/other-post"/>">Other's Status</a></td></tr>
	       <!-- <tr><td><a href="<c:url value="/user-homepage/audio-video"/>">Audio/Video</a></td></tr> --> 
	    </table>
    
	   <!--   <form action="user-homepage" method="post">
			<table style="float:left; width:100px;">
				<tr><td>Status Update:<textarea name="content" rows="4" cols="50"></textarea></td></tr>
				<tr><td><input type="submit" value="Post"/>
				</table>
		</form> -->
		<form:form action="user-homepage" method="post" modelAttribute="status">
			<table style="float:left; width:100px;">
				<tr><td><label for="statusInput">Status Update:</label></td></tr>
				<tr><td><form:textarea path="content" id="statusInput" rows="4" cols="50"></form:textarea></td></tr>
				<tr><td><input type="submit" value="Post"/>
			</table>
		</form:form>
	</body>
</html>