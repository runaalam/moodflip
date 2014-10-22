//
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
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
				  	<li><a href="/card-game">Card game</a></li>
					<li class="active"><a href="<c:url value="/card-game/myCards"/>">My cards</a></li>
				  	<li><a href="<c:url value="/card-game/customCards"/>">Custom Cards</a></li>
				  	<li><a href="#">Game Stats</a></li>
				</ul>
			</div>
		</div>
		<div class="col-md-10">
		<ol class="breadcrumb">
			<li><a href="<c:url value="/"/>">Home</a></li>
			<li><a href="<c:url value="/card-game"/>">card game</a></li>
			<li class="active">My cards</li>
		</ol>
			<h1>My Cards</h1>
			<c:forEach var="card" items="${cards}">
				<c:out value="${card.title}"/><br/>
				Level: <c:out value="${card.level }"/>	
				Symptom: <c:out value="${card.symptom }"/><br/>
				<c:out value="${card.intro }"/><br/>
				<a href="<c:url value="/card-game/myCards?play=${card.cardId}"/>">Play card</a><br/>
			</c:forEach>
		</div>
	</div>
</div>
</body>
</html>