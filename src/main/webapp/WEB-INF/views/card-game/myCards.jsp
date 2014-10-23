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
			<h1>My Cards</h1>
			<c:forEach var="card" items="${cards}">
				<c:out value="${card.title}"/><br/>
				Level: <c:out value="${card.level }"/>	
				Symptom: <c:out value="${card.symptom }"/><br/>
				<c:out value="${card.intro }"/><br/>
				<a href="<c:url value="/card-game/myCards?play=${card.cardId}"/>">Play card</a><br/>
			</c:forEach>
		</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>