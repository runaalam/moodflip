<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<h3>Questions</h3>
	<c:choose>
		<c:when test="${!empty quesList}">
			<form:form method="post" modelAttribute="assessment">
		
			<table border="1">
				<tr><c:out value="Assessment Questions" /></tr>
				<c:forEach items="${quesList}" var="question" varStatus="row">
					<tr>
						<td>${question.id}</td>
						<td>${question.text}</td>
      					
           				<form:input path="responseList[${row.index}].questionId" type="hidden" name="${status.expression}"
                			id="${status.expression}" value="${question.id}" />
				
        				<td>
            			<form:radiobuttons path="responseList[${row.index}].answerId" delimiter="<br>"
                			items="${ansList}" itemLabel="text" itemValue="id"/>
                		</td>
					</tr>
				</c:forEach>
			</table>
			<br><br>
			  <input type="submit" value="submit"/>
			</form:form>
		</c:when>
		
		<c:when test="${empty quesList}">
			There is no questions.
		</c:when>
	</c:choose>

</body>
</html>