<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<HTML>
	<jsp:include page="../fragments/headTag.jsp"/>
<body> 
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="container">
	<div class="row">
    	<!-- include Sidebar --> 	
	    <jsp:include page="../fragments/bodySideBar.jsp"/>
	    <div class="col-xs-12 col-sm-9">
	    	<h1>Card browser</h1>
	    	<c:choose>
	    	<c:when test="${!empty card}">
				<div class="panel panel-default">
						<div class="panel-heading text-center">
							<h3 class="panel-title">${card.title}</h3>
							<small>Level: ${card.level} - 
							Symptom: ${card.symptom.text} - 
							</small>
						</div>
						<div class="panel-body">
							<p>${card.intro}</p>

							<a class="btn btn-default" href="<c:url value="/card-game/cardBrowser?addToMyCards=${card.cardId}"/>">Add to Custom Cards</a>
			    			<a class="btn btn-default" href="<c:url value="/card-game/cardBrowser?addToPlaylist=${card.cardId}"/>">Add to playlist</a>				    		
						</div>
					</div>					
	    	</c:when>
	    	<c:when test="${!empty cards}">
	    		<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
				    	<table class="table table-condensed">
				    		<thead>
								<tr>
									<th>Title</th>
									<th>Level</th>
									<th>Symptom</th>
									<th>Average Rating</th>
									<th>Completions</th>
									<th>Attempts</th>
									<th>Id</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
				    	<c:forEach items="${cards}" var="c">
				    		<tr>
				    			<td><a href="<c:url value="/card-game/cardBrowser?view=${c.cardId}"/>">${c.title}</a></td>
				    			<td>${c.level}</td>
				    			<td>${c.symptom.text}</td>
				    			<td>${c.avgRating}</td>
				    			<td>${c.completions}</td>
				    			<td>${c.attempts}</td>
				    			<td>${c.cardId}</td>
				    			<td><a href="<c:url value="/card-game/cardBrowser?addToMyCards=${c.cardId}"/>">Add to Custom Cards</a></td>
				    			<td><a href="<c:url value="/card-game/cardBrowser?addToPlaylist=${c.cardId}"/>">Add to playlist</a></td>
				    		</tr>
				    	</c:forEach>
				    	</tbody>
				    	</table>
				    	</div>
					</div>
				</div>
	    	</c:when>
	    	<c:otherwise>
	    		<p>There are no cards in the system yet.  Create new cards by going to <a href="<c:url value="customCards"/>">custom cards</a></p>
	    	</c:otherwise>
	    	</c:choose>
	    </div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>