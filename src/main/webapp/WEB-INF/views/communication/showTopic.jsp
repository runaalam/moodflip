<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html ng-app="moodFlip">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	
	<div ng-controller="ForumCtrl" ng-init="initTopicAndComments(<c:out value="${topicId}"/>)">
	
	<h3>
		<a href="<c:url value="/forum" />">Forum</a> > <a
			href="<c:url value="/forum/{{topic.forum.id}}" />">{{topic.forum.forumName}}</a> >
		{{topic.name}}
	</h3>
	
	<h2>{{topic.name}}</h2>
	<br>
	User: {{topic.userId}}
	<br>
	Created At: {{topic.createdAt}}
	<br>
	Edited At: {{topic.editedAt}}
	<br>
	<p ng-bind-html="topic.content | markdown"></p>
	<br>
	<a href="<c:url value="/forum/topic/edit/{{topic.id}}"/>">Edit</a>
	<a href="<c:url value="/forum/topic/delete/{{topic.id}}"/>">Delete</a>
	<br>
	Up Vote {{topic.upVote}}<a href="" ng-click="upVoteTopic()" >+</a>
	<br>
	Down Vote {{topic.downVote}}<a href="" ng-click="downVoteTopic()" >-</a>
	
	<h2>Comments</h2>
	
	<hr>

		<div ng-show="comments.length">
		<div ng-repeat="comment in comments">
		
		User: {{comment.userId}}
		<br>
		Created At: {{comment.createdAt}}
		<br>
		Edited At: {{comment.editedAt}}
		<br>
		<p ng-bind-html="comment.content | markdown"></p>
		<br>
		<a href="<c:url value="/forum/comment/edit/{{comment.id}}"/>">Edit</a>
		<a href="" ng-click="deleteComment(comment.id)" >Delete</a>
		<br>
		Up Vote {{comment.upVote}}<a href="" ng-click="upVoteComment(comment.id)">+</a>
		<br>
		Down Vote {{comment.downVote}}<a href="" ng-click="downVoteComment(comment.id)">-</a>
		
		<hr>
		
		</div>
		</div>
		<div ng-hide="comments.length">No comment</div>

	<br>
	<a href="<c:url value="/forum/topic/{{topic.id}}/newComment"/>">Reply</a>
	<br>
	
	</div>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-sanitize.min.js"></script>
<script src="<c:url value="/resources/comm/js/main.js" />"></script>
<script src="<c:url value="/resources/comm/js/forum.js" />"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/showdown/0.3.1/showdown.min.js"></script>

</html>