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
	    	<h1>Card browser</h1>
	    	<c:if test="${!empty card}">
		    	<div id="cardView">
		    		<table>
		    			<tr><td>${card.title}</td></tr>
		    			<tr><td>Level: ${card.level}</td></tr>
		    			<tr><td>Symptom: ${card.symptom.text}</td></tr>
		    			<tr><td>${card.intro}</td></tr>
		    		</table>
		    		<td><a href="<c:url value="/card-game/cardBrowser?add=${card.cardId}"/>">Add card</a></td>
		    	</div>
	    	</c:if>
	    	
	    	<c:if test="${!empty cards}">
		    	<table>
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
		    	<c:forEach items="${cards}" var="c">
		    		<tr>
		    			<td><a href="<c:url value="/card-game/cardBrowser?view=${c.cardId}"/>">${c.title}</a></td>
		    			<td>${c.level}</td>
		    			<td>${c.symptom.text}</td>
		    			<td>${c.avgRating}</td>
		    			<td>${c.completions}</td>
		    			<td>${c.attempts}</td>
		    			<td>${c.cardId}</td>
		    			<td><a href="<c:url value="/card-game/cardBrowser?add=${c.cardId}"/>">Add</a></td>
		    		</tr>
		    	</c:forEach>
		    	</table>
	    	</c:if>
	    </div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>