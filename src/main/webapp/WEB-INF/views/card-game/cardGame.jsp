<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Card game</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	    	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	    		<span class="icon-bar"></span>
	      	</button>
	      <a class="navbar-brand" href="#">MoodFlip</a>
	    </div>
	    
	    <p class="navbar-text navbar-right">100 pts Level 2</p>
	    <p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">User123</a></p>
	</div>
</nav>
<div class="container">
	<div class="row">
		<div class="col-md-2">
			<div class="nav">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="<c:url value="/card-game"/>">Card game</a></li>
				  	<li><a href="<c:url value="/card-game/myCards"/>">My Cards</a></li>
				  	<li><a href="<c:url value="/card-game/customCards"/>">Custom Cards</a></li>
				  	<li><a href="#">Game Stats</a></li>
				</ul>
			</div>
		</div>
		<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="<c:url value="/"/>">Home</a></li>
			<li class="active">Card game</li>
		</ol>
			<h1>Card game main page</h1>
			<c:choose>
				<c:when test="${(!empty mission) or (!empty cardSurvey)}">
					<c:out value="${mission.card.title}"/> <c:out value="${mission.card.level}"/>  <c:out value="${mission.card.symptom.text}"/> <br/>
					<c:choose>
						<c:when test="${!empty mission}">
							<sf:form method="POST" modelAttribute="mission">
								<label><c:out value="${mission.text}"/></label>
								<input type="submit" name="newCard" value="New Card">
								<input type="submit" name="nextMission" value="Next Mission">
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
				</c:when>
				<c:otherwise>
					You do not have a mission yet. When you're ready, go to <a href="<c:url value="card-game/myCards"/>">My Cards</a> on the side bar
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
</body>
</html>