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
			<h1>Card game main page</h1>
			<c:choose>
				<c:when test="${(!empty mission) or (!empty cardSurvey)}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2 class="panel-title"><c:out value="${mission.card.title}"/></h2>
							<small>Level: <c:out value="${mission.card.level}"/>
							Symptom: <c:out value="${mission.card.symptom.text}"/> </small>
						</div>
						<div class="panel-body">
							<c:choose>
								<c:when test="${!empty mission}">
									<sf:form method="POST" modelAttribute="mission">
										<label><c:out value="${mission.text}"/></label>
										<input class="btn btn-default" type="submit" name="newCard" value="New Card">
										<input class="btn btn-default" type="submit" name="nextMission" value="Next Mission">
									</sf:form>
								</c:when>
								<c:when test="${!empty cardSurvey}">
									<sf:form method="POST" modelAttribute="cardSurvey">
										Conclusion: <label><c:out value="${cardSurvey.card.outro}"/></label>
										Rate this card: <label><c:out value="${cardSurvey.question}"/></label>
										<sf:select path="answer">
											<sf:option value="" label="--Please Select"/>
											<sf:options items="${answers}" itemLabel="text"/>
										</sf:select>
										<input type="submit" name="finish" value="Finish"/>
									</sf:form>
								</c:when>
							</c:choose>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					You do not have a mission yet. When you're ready, go to <a href="<c:url value="myCards"/>">My Cards</a> on the side bar
				</c:otherwise>
			</c:choose>
		</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>