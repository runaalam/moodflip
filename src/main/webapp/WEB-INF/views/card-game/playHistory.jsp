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
			<h1>Play History</h1>
			<table class="table table-condensed">
					<thead>
						<tr>
							<th>Title</th>
							<th>Points</th>
							<th>Attempts</th>
							<th>Completions</th>
							<th>id</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mainPlayHistoryItems}" var="i">
							<tr>
								<td><a href="<c:url value="/card-game/playHistory?cardId=${i.cardId}"/>"><c:out value="${i.title}"/></a></td>
								<td><c:out value="${i.points}"/></td>
								<td><c:out value="${i.attempts}"/></td>
								<td><c:out value="${i.completions}"/></td>
								<td><c:out value="${i.cardId}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<table class="table table-condensed">
					<thead>
						<tr>
							<th>Date</th>
							<th>Points</th>
							<th>Complete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cardPlayHistoryItems}" var="ce">
							<tr>
								<td><c:out value="${ce.date}"/></td>
								<td><c:out value="${ce.points}"/></td>
								<td><c:out value="${ce.complete}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>