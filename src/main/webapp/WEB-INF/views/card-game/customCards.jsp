<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Custom cards</title>
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
				  	<li><a href="<c:url value="/card-game"/>">Card game</a></li>
				  	<li><a href="#">My Cards</a></li>
					<li class="active"><a href="<c:url value="/card-game/customCards"/>">Custom cards</a></li>
				  	<li><a href="#">Game Stats</a></li>
				</ul>
			</div>
		</div>
		<div class="col-md-10">
			<ol class="breadcrumb">
		    	<li><a href="<c:url value="/"/>">Home</a></li>
		  		<li><a href="<c:url value="/card-game"/>">Card game</a></li>
		  		<li class="active">Custom Cards</li>
			</ol>
			<h1>Custom cards</h1><br/>
			<a href="<c:url value="/card-game/customCards?new"/>">Create new card</a>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">My Custom Cards</h3>
				</div>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th><form:checkbox path="" value=""/></th>
							<th>Title</th>
							<th>Level</th>
							<th>Symptom</th>
							<th>Average Rating</th>
							<th>Completions</th>
							<th>Attempts</th>
							<th>Id</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${model.customCards}" var="card">
							<tr>
								<td><a href="<c:url value="/card-game/customCards?delete=${card.cardId}"/>">Delete</td>
								<td><a href="<c:url value="/card-game/customCards?edit=${card.cardId}"/>"><c:out value="${card.title}"/></a></td>
								<td><c:out value="${card.level}"/></td>
								<td><c:out value="${card.symptom.text}"/></td>
								<td><c:out value="${card.avgRating}"/></td>
								<td><c:out value="${card.completions}"/></td>
								<td><c:out value="${card.attempts}"/></td>
								<td><c:out value="${card.cardId}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>