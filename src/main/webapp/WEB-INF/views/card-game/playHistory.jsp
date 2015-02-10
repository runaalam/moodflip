<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<HTML>
	<jsp:include page="../fragments/headTag.jsp"/>
	<style type="text/css">
		.hiddenRow {padding: 0 !important;}
	</style>
<body> 
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="container">
	<div class="row">
    	<!-- include Sidebar --> 	
	    <jsp:include page="../fragments/bodySideBar.jsp"/>
	    <div class="col-xs-12 col-sm-9">
			<h1>Play History</h1>
			<div class="panel panel-default">
			 	<div class="panel-body">
			 		<div class="table-responsive">
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
							<c:forEach items="${mainPlayHistoryItems}" var="i" varStatus="status">
								<tr>
									<td><a data-toggle="collapse" class="cardRow" href="#expand${i.cardId}"><c:out value="${i.title}"/></a></td> <!--  <c:url value="/card-game/playHistory?cardId=${i.cardId}"/> -->
									<td><c:out value="${i.points}"/></td>
									<td><c:out value="${i.attempts}"/></td>
									<td><c:out value="${i.completions}"/></td>
									<td><c:out value="${i.cardId}"/></td>
								</tr>
								<tr>
									<td colspan="5" class="hiddenRow">
										<div id="expand${i.cardId }" class="panel-collapse collapse">
											<table class="table table-condensed">
											<thead>
												<tr>
													<th>Date</th>
													<th>Points</th>
													<th>Complete</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><c:out value="${details[status.index].date}"/></td>
													<td><c:out value="${details[status.index].points}"/></td>
													<td><c:out value="${details[status.index].complete}"/></td>
												</tr>
											</tbody>
										</table>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
			
			
		</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
<!--  <script src="<c:url value="/resources/card-game/js/playHistory.js" />" type="text/javascript"></script> -->
</html>