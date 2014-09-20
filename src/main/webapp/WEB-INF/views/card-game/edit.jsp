<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New card</title>
<style>
		.error { color: red; }
</style>
</head>
<body>
<sf:form method="POST" modelAttribute="card">
	<table>
		<tr>
			<td><label for="cardTitle">Title</label></td>
			<td><sf:input path="title" id="cardTitle"/></td>
			<td><sf:errors path="title" cssClass="error"/></td>
		</tr>	
		<tr>
			<td><label for="cardLevel">Difficulty Level</label></td>
			<td><sf:input path="level" id="cardLevel"/></td>
			<td><sf:errors path="level" cssClass="error"/></td>
		</tr>
		<tr>
			<td><label for="cardSymptom">Symptom group</label></td>
			<td>
				<sf:select path="symptom">
					<sf:option value="" label="--Please Select"/>
					<sf:options items="${symptoms}" itemLabel="text"/>
				</sf:select>
			</td>
			<td><sf:errors path="symptom" cssClass="error"/></td>
		</tr>
		<tr>
			<td><label for="cardIntro">Introduction</label></td>
			<td><sf:textarea path="intro" id="cardIntro"/></td>
			<td><sf:errors path="intro" cssClass="error"/></td>
		</tr>
<!--  		<tr>
			<td><label for="cardMission">Mission</label></td>
			<td><sf:textarea path="" id="cardMission"/></td>
		</tr> -->
		<tr>
			<td><label for="cardOutro">Completion message</label></td>
			<td><sf:textarea path="outro" id="cardOutro"/></td>
			<td><sf:errors path="outro" cssClass="error"/></td>
		</tr>
	</table>
	<sf:hidden path="cardId"/>
	<input type="submit" value="Save card" />
</sf:form>
<c:out value="${status}"/>
</body>
</html>