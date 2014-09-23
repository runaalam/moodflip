<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New card</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<style>
		.error { color: red; }
</style>
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
			<ul class="nav nav-pills nav-stacked">
			  	<li><a href="<c:url value="/card-game"/>">Card game</a></li>
			  	<li><a href="#">My Cards</a></li>
				<li class="active"><a href="<c:url value="/card-game/customCards"/>">Custom cards</a></li>
			  	<li><a href="#">Game Stats</a></li>
			</ul>
		</div>
		<div class="col-md-10">
			<ol class="breadcrumb">
				<li><a href="<c:url value="/"/>">Home</a></li>
			  	<li><a href="<c:url value="/card-game"/>">Card game</a></li>
			  	<li><a href="<c:url value="/card-game/customCards"/>">Custom Cards</a></li>
			  	<li class="active">Create/Edit</li>
			</ol>
			<h1>Create/Edit card</h1><br/>
			<sf:form method="POST" modelAttribute="card" class="form-horizontal" role="form">
				<div class="form-group">
					<label for="cardTitle" class="col-sm-2 control-label">Title</label>
					<div class="col-sm-5">
						<sf:input path="title" id="cardTitle" class="form-control" placeholder="Title"/>
						<sf:errors path="title" cssClass="error"/>
					</div>	
				</div>				
				<div class="form-group">	
					<label for="cardLevel" class="col-sm-2 control-label">Difficulty Level</label>
					<div class="col-sm-5">
						<sf:input path="level" id="cardLevel" class="form-control" placeholder="Level"/>
						<sf:errors path="level" cssClass="error"/>
					</div>
				</div>
				<div class="form-group">	
					<label for="cardSymptom" class="col-sm-2 control-label">Symptom group</label>
					<div class="col-sm-5">
						<sf:select path="symptom" class="form-control">
							<sf:option value="" label="--Please Select"/>
							<sf:options items="${symptoms}" itemLabel="text"/>
						</sf:select>
						<sf:errors path="symptom" cssClass="error"/>
					</div>
				</div>	
				<div class="form-group">	
					<label for="cardIntro" class="col-sm-2 control-label">Introduction</label>
					<div class="col-sm-5">
						<sf:textarea path="intro" id="cardIntro" class="form-control" rows="3"/>
						<sf:errors path="intro" cssClass="error"/>
					</div>
				</div>	
			<!--  		<tr>
						<td><label for="cardMission">Mission</label></td>
						<td><sf:textarea path="" id="cardMission"/></td>
					</tr> -->
				<div class="form-group">
					<label for="cardOutro" class="col-sm-2 control-label">Completion message</label>
					<div class="col-sm-5">
						<sf:textarea path="outro" id="cardOutro" class="form-control" rows="3"/>
						<sf:errors path="outro" cssClass="error"/>
					</div>
				</div>	
				
				<sf:hidden path="cardId"/>
				<input type="submit" value="Save card" class="btn btn-primary" />
			</sf:form>
			<c:out value="${status}"/>
		</div>
	</div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>