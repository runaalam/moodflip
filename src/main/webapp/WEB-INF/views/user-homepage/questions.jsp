<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
	<jsp:include page="../fragments/headTag.jsp" />
	<title><fmt:message key="userHomepage.assessment.questions" /></title>
</head>
<body>
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="container">
	<jsp:include page="../fragments/bodySideBar.jsp" />
		<div class="col-xs-12 col-sm-9">
			<form:form method="post" modelAttribute="assessment">
		 		<div class="panel panel-default">
					<c:choose>
						<c:when test="${!empty quesList}">
							<table class="table table-condensed">
								<thead><tr><th></th>
										<th><h5><c:out value="Assessment Questions" /></h5></th>
											<c:forEach items="${ansList}" var="answer">
													<th style="width: 10%"><h4><small><c:out value="${answer.text}" /></small></h4>
										</th></c:forEach>
								</tr></thead>
								<c:forEach items="${quesList}" var="question" varStatus="row">
									<tr>
										<td>${question.id}</td>
										<td>${question.text}</td>
										<form:input path="responseList[${row.index}].question.id"
											type="hidden" name="${status.expression}"
											id="${status.expression}" value="${question.id}" />
										<c:forEach items="${ansList}" var="answer">
											<td class="col-md-1"><form:radiobutton
													path="responseList[${row.index}].answer.value"
													value="${answer.id}" /></td>
										</c:forEach>
									</tr>
								</c:forEach>
							</table>
						<hr><p align="center"><input class="btn btn-primary" type="submit" value="submit"></p>
				</c:when>
				<c:when test="${empty quesList}"><fmt:message key="userHomepage.assessment.questions.empty"/></c:when>
			</c:choose>
			</div> <!-- panel panel-default -->
		</form:form>
	</div> <!-- col-xs-12 col-sm-9 -->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp" />
</body>
</html>