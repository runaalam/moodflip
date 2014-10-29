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
			<h1>Play game</h1>
			<c:choose>
				<c:when test="${(!empty mission) or (!empty cardSurvey)}">
					<div class="panel panel-default text-center">
						<div class="panel-heading">
							<div class="row">
								<c:if test="${!empty mission}">
									<div class="hidden-xs col-sm-3">
										<a class="btn btn-default pull-left" href="<c:url value="/card-game?newCard"/>">New Card</a>
									</div>
									<div class="col-xs-12 col-sm-6">
										<h3 class="panel-title"><c:out value="${mission.card.title}"/></h3>
										<small>Level: <c:out value="${mission.card.level}"/> |
										Symptom: <c:out value="${mission.card.symptom.text}"/> | Mission: ${taskIndex } / ${taskCount }</small>
									</div>
									<div class="hidden-sm hidden-md hidden-lg col-xs-6">
										<a class="btn btn-default pull-left" href="<c:url value="/card-game?newCard"/>">New Card</a>
									</div>
									<div class="col-xs-6 col-sm-3">
										<a class="btn btn-default pull-right" href="<c:url value="/card-game?nextMission"/>">Next Mission</a>
									</div>
								</c:if>
								<c:if test="${!empty cardSurvey}">
									<h3 class="panel-title"><c:out value="${cardSurvey.card.title}"/></h3>
									<small>Level: <c:out value="${cardSurvey.card.level}"/>
									Symptom: <c:out value="${cardSurvey.card.symptom.text}"/> </small>
								</c:if>
							</div>
						</div>
						<div class="panel-body">
							<c:choose>
								<c:when test="${!empty mission}">
									<p><c:out value="${mission.text}"/></p>
								</c:when>
								<c:when test="${!empty cardSurvey}">
									<sf:form method="POST" modelAttribute="cardSurvey">
										Conclusion: <p><c:out value="${cardSurvey.card.outro}"/></p>
										Rate this card: <p><c:out value="${cardSurvey.question}"/></p>
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