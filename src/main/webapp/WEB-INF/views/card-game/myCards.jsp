<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
	<jsp:include page="../fragments/headTag.jsp"/>
	<style type="text/css">
		.item{
		    background: #333;    
		    text-align: center;
		    height: 400px !important;
		    padding: 30px 100px 30px 100px;
		}
		.carousel{
		    margin-top: 20px;
		}
		.card-body{
		 	max-height: 220px;
    		overflow-y: auto; 
		}
			
	</style>
<body> 
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="container">
	<div class="row">
    	<!-- include Sidebar --> 	
	    <jsp:include page="../fragments/bodySideBar.jsp"/>
	    <div class="col-xs-12 col-sm-9">
			<h1>My Cards</h1>
			<c:if test="${!empty cards}">
				<div class="panel panel-default">
					<div class="panel-body">
						<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
							<!-- Indicators -->
							<ol class="carousel-indicators">
								<c:forEach var="card" items="${cards}" varStatus="i">
									<li data-target="#carousel-example-generic" data-slide-to="${i.index}" <c:if test="${i.first}">class="active"</c:if>></li>
								</c:forEach>
							</ol>
							
							<!-- Wrapper for slides -->
							<div class="carousel-inner">
								<c:forEach var="card" items="${cards}" varStatus="i">
									<div class="item <c:if test="${i.first}">active</c:if>">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h3 class="panel-title"><c:out value="${card.title}"/></h3>
												Level: <c:out value="${card.level }"/>
												Symptom: <c:out value="${card.symptom }"/><br/>
												<a href="<c:url value="/card-game/myCards?play=${card.cardId}"/>">Play card</a><br/>
											</div>
											<div class="panel-body card-body">
												<p><c:out value="${card.intro }"/></p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<!-- Controls --> 
							  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
							    <span class="glyphicon glyphicon-chevron-left"></span>
							  </a>
							  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
							    <span class="glyphicon glyphicon-chevron-right"></span>
							  </a>
						</div>
						<table class="table table-condensed table-hover">
							<thead>
								<tr>
									<th>Action</th>
									<th>Title</th>
									<th>Level</th>
									<th>Symptom</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="card" items="${cards}" varStatus="i">
									<tr>
										<td><a href="<c:url value="/card-game/myCards?delete=${card.cardId}"/>">Delete</a></td>
										<td><a href="#" data-target="#carousel-example-generic" data-slide-to="${i.index}">${card.title}</a></td>
										<td>${card.level}</td>
										<td>${card.symptom.text}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:if>
			<c:if test="${empty cards }">
				<p>You don't have anything in your playlist yet.  Here are a few options: 
					<ol>
						<li>go to the <a href="<c:url value="cardBrowser"/>">card browser</a> and choose cards to add to your playlist</li>
						<li>if you have completed an <a href="<c:url value="/user-homepage/depression-assessment"/>">assessment</a> or logged <a href="<c:url value="/mood-tracking/data"/>">mood tracking data</a>, you can choose recommend cards</li>
						<li>if you just want to start playing, choose lucky trip and 3 randomly chosen cards will be added to your playlist</li>
					</ol>
				</p>
			</c:if>
			<a class="btn btn-default" href="<c:url value="/card-game/myCards?recommend"/>">Recommend cards</a>
			<a class="btn btn-default" href="<c:url value="/card-game/myCards?random"/>">Lucky Trip</a>
		</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>