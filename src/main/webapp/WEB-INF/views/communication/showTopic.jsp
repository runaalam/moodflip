<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html ng-app="moodFlip">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/comm/css/forums.css" />">
</head>
<body>
	
	<%@ include file="/WEB-INF/views/navbar.jsp"%>
	
	<div class="container">
	
	<div ng-controller="ForumsCtrl" ng-init="initTopicAndComments(<c:out value="${topicId}"/>)">
	
	<ol class="breadcrumb">
		<li><a href="<c:url value="/forums" />">Forums</a></li>
  		<li><a href="<c:url value="/forums/{{topic.forum.id}}" />">{{topic.forum.forumName}}</a></li>
  		<li class="active">{{topic.name}}</li>
	</ol>
	
	<div class="row">
	<div class="col-md-8">
	<h2>{{topic.name}}</h2>
	</div>
	<div class="col-md-4">
	<div class="pull-right forum-btn">
		<a href="<c:url value="/forums/topic/{{topic.id}}/newComment"/>"><button type="button" class="btn btn-primary btn-lg">Reply</button></a>
	</div>
	</div>
	</div>
	
	<div class="row topic">
	<div class="col-md-2">
		<img src="<c:url value="/resources/comm/pic/default_avatar.png" />" alt="avatar" class="img-circle user-pic center-block">
		<h4 class="text-center">{{topic.user.username}}</h4>
		<span ng-hide="topic.user.username == '<sec:authentication property="principal.username" />'">
		<a target="_blank" href="<c:url value="/pm/{{topic.user.id}}"/>"><button type="button" class="btn btn-default center-block"><span class="glyphicon glyphicon-comment"></span> PM</button></a>
		</span>
	</div>
	
	<div class="col-md-10">
		<div class="row">
			<div class="col-md-12">
				<p class="text-right">by {{topic.user.username}} &raquo; {{topic.createdAt}}
				<br>
				<span ng-show="topic.editedAt"> Last edited: {{topic.editedAt}}</span>
				</p>
			</div>
		</div>
		<div class="row post-content">
			<div class="col-md-12">
				<p ng-bind-html="topic.content | markdown"></p>
			</div>
		</div>
		<div class="row post-btn">
			<div class="col-md-12">
				<span ng-show="topic.user.username == '<sec:authentication property="principal.username" />'">
				<a href="<c:url value="/forums/topic/edit/{{topic.id}}"/>"><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span> Edit</button></a>
				<a href="<c:url value="/forums/topic/delete/{{topic.id}}"/>"><button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button></a>
				</span>
				
				<div class="pull-right">
				
				<button type="button" class="btn btn-default" ng-click="upVoteTopic()">
 					<span class="glyphicon glyphicon-thumbs-up"></span> Up Vote <span class="badge">{{topic.upVote}}</span>
				</button>
				
				<button type="button" class="btn btn-default" ng-click="downVoteTopic()">
 					<span class="glyphicon glyphicon-thumbs-down"></span> Down Vote <span class="badge">{{topic.downVote}}</span>
				</button>

				</div>
			</div>
		</div>
	</div>
	</div>
	
	<div class="row topic">
		<div class="col-md-12">
			<h3>Suggested Cards</h3>
			<div class="row cards">
				<div class="span3 card" ng-repeat="card in cards">
				<div class="thumbnail">
			      <div class="caption">
			      	<div class="text-right"><small>Card ID: {{card.card.cardId}}</small></div>
			        <h3>{{card.card.title}}</h3>
			        <p>Level: {{card.card.level}}</p>
			        <p>Symptom: {{card.card.symptom.text}}</p>
			        <p>Average Rating: {{card.card.avgRating}}</p>
			        <p><small>Completions: {{card.card.completions}}</small>
			        <br>
			        <small>Attempts: {{card.card.attempts}}</small></p>
			      </div>
			    </div>
				</div>
			</div>
			<div class="pull-right forum-btn">
			<a href="<c:url value="/forums/topic/{{topic.id}}/suggestCard"/>"><button type="button" class="btn btn-primary">Suggest Card</button></a>
			</div>
		</div>
	</div>
	
	<div ng-show="comments.length">
	<div ng-repeat="comment in comments">
	
	<div class="row topic">
	<div class="col-md-2">
		<img src="<c:url value="/resources/comm/pic/default_avatar.png" />" alt="avatar" class="img-circle user-pic center-block">
		<h4 class="text-center">{{comment.user.username}}</h4>
		<span ng-hide="comment.user.username == '<sec:authentication property="principal.username" />'">
		<a target="_blank" href="<c:url value="/pm/{{comment.user.id}}"/>"><button type="button" class="btn btn-default center-block"><span class="glyphicon glyphicon-comment"></span> PM</button></a>
		</span>
	</div>
	
	<div class="col-md-10">
		<div class="row">
			<div class="col-md-12">
				<p class="text-right">by {{comment.user.username}} &raquo; {{comment.createdAt}}
				<br>
				<span ng-show="comment.editedAt"> Last edited: {{comment.editedAt}}</span>
				</p>
			</div>
		</div>
		<div class="row post-content">
			<div class="col-md-12">
				<p ng-bind-html="comment.content | markdown"></p>
			</div>
		</div>
		<div class="row post-btn">
			<div class="col-md-12">
				<span ng-show="comment.user.username == '<sec:authentication property="principal.username" />'">
				<a href="<c:url value="/forums/comment/edit/{{comment.id}}"/>"><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span> Edit</button></a>
				<button type="button" class="btn btn-danger" ng-click="deleteComment(comment.id)"><span class="glyphicon glyphicon-trash"></span> Delete</button>
				</span>
				
				<div class="pull-right">
				
				<button type="button" class="btn btn-default" ng-click="upVoteComment(comment.id)">
 					<span class="glyphicon glyphicon-thumbs-up"></span> Up Vote <span class="badge">{{comment.upVote}}</span>
				</button>
				
				<button type="button" class="btn btn-default" ng-click="downVoteComment(comment.id)">
 					<span class="glyphicon glyphicon-thumbs-down"></span> Down Vote <span class="badge">{{comment.downVote}}</span>
				</button>

				</div>
			</div>
		</div>
	</div>
	</div>
	
	</div>
	</div>
		
	<!-- 
		<div ng-hide="comments.length">
		<div class="row topic">
		<div class="col-md-12">
			<p>No comment</p>
		</div>
		</div>
		</div>
	 -->
		
	<div class="row">
	<div class="col-md-12">
	<div class="pull-right forum-btn">
		<a href="<c:url value="/forums/topic/{{topic.id}}/newComment"/>"><button type="button" class="btn btn-primary btn-lg">Reply</button></a>
	</div>
	</div>
	</div>
	
	</div>
	</div>
</body>

<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-sanitize.min.js"></script>
<script src="<c:url value="/resources/comm/js/main.js" />"></script>
<script src="<c:url value="/resources/comm/js/forums.js" />"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/showdown/0.3.1/showdown.min.js"></script>

</html>