<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html ng-app="moodFlip">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Suggest Card - Forums - <fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/comm/css/forums.css" />">
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</head>
<body>

<%@ include file="/WEB-INF/views/navbar.jsp"%>

<div class="container">

<div ng-controller="CardSuggestCtrl" ng-init="init(${topicId})" >

	<a href="<c:url value="/forums/topic/${topicId}" />">&laquo; Back to Topic</a>
	
	<h1>Suggest Card</h1>

<div class="row">
<div class="col-md-4">
<div class="dropdown dropdown-symptoms">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
    {{selectedSymptom}}
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
  	<li role="presentation"><a role="menuitem" tabindex="-1" href="" ng-click="setSymptomAndListCards('All Symptoms')">All Symptoms</a></li>
    <li ng-repeat="symptom in symptoms" role="presentation"><a role="menuitem" tabindex="-1" href="" ng-click="setSymptomAndListCards(symptom.text)">{{symptom.text}}</a></li>
  </ul>
</div>
</div>
</div>

<div class="row" ng-repeat="column in columns">
  <div class="col-md-3" ng-repeat="card in column">
    <div class="thumbnail">
      <div class="caption">
      	<div class="text-right"><small>Card ID: {{card.cardId}}</small></div>
        <h3 class="h-card">{{card.title}}</h3>
        <p>Level: {{card.level}}</p>
        <p>Symptom: {{card.symptom.text}}</p>
        <p>Average Rating: {{card.avgRating}}</p>
        <p><small>Completions: {{card.completions}}</small>
        <br>
        <small>Attempts: {{card.attempts}}</small></p>
        <p><a target="_blank" href="<c:url value="/card-game/cardBrowser?view={{card.cardId}}" />">More Details</a></p>
        <label class="btn btn-default btn-block" data-toggle="button" ng-click="setSelected(card.cardId)">Select</label>
      </div>
    </div>
  </div>
</div>

<div class="row">
	<div class="col-md-12">
	<div class="pull-right forum-btn">
		<button type="button" class="btn btn-primary btn-lg" ng-click="postSuggest()">Suggest</button>
	</div>
	</div>
</div>

</div>
</div>
<%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
</html>