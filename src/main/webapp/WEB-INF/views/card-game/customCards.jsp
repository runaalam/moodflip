<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
	<jsp:include page="../fragments/headTag.jsp"/>
<body> 
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="container">
	<div class="row">
    	<!-- include Sidebar --> 	
	    <jsp:include page="../fragments/bodySideBar.jsp"/>
	    <div class="col-xs-12 col-sm-9">
	    <div class="alert alert-success hide">
		    <a href="#" class="close" data-dismiss="alert">&times;</a>
		</div>
			<h1>Customise cards</h1><br/>
			<a href="<c:url value="/card-game/customCards?new"/>">Create new card</a>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">My Custom Cards</h3>
				</div>
				<div class="table-responsive">
				<table class="table table-condensed">
					<thead>
						<tr>
							<th>Action</th>
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
						<c:forEach items="${model.customCards}" var="card" varStatus="i">
							<tr>
								<td>
									<a href="<c:url value="/card-game/customCards?delete=${card.cardId}"/>">Delete</a>
									<a href="#" class="share" data-toggle="popover" title="Share card with user">Share</a>
									<div id="popover-content" class="hide">
										<form class="form-inline" role="form">
									  		<input type="text" name="user" class="form-control" placeholder="Enter user"/>
									  		<input type="hidden" id="test" name="cardId" value="${card.cardId}"/>
									  		<input type="submit" value="Send" class="btn btn-default"/>	
									  		<div id="shareStatus"></div>
								  		</form>
									</div>
								</td>
								<td><a href="<c:url value="/card-game/customCards?edit=${card.cardId}"/>"><c:out value="${card.title}"/></a></td>
								<td><c:out value="${card.level}"/></td>
								<td><c:out value="${card.symptom.text}"/></td>
								<td><c:out value="${fn:substring(card.avgRating, 0, 3) }"/></td>
								<td><c:out value="${card.completions}"/></td>
								<td><c:out value="${card.attempts}"/></td>
								<td><c:out value="${card.cardId}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div> <!-- panel panel-default -->
		</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
<script src="<c:url value="/resources/card-game/js/customCards.js" />" type="text/javascript"></script>
</html>
