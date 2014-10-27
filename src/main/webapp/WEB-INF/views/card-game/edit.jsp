<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
			<h1>Create/Edit card</h1><br/>
			<sf:form method="POST" modelAttribute="card" class="form-horizontal editCardForm" role="form">
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
				<div id="missionsDiv">
					<c:choose>
						<c:when test="${fn:length(card.tasks) > 1}">  <!-- old card -->
							<c:set var="end" scope="request" value="${fn:length(card.tasks) - 1}"/>
						</c:when>
						<c:otherwise> <!--  new card -->
							<c:set var="end" scope="request" value="${1}"/>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="0" end="${lastMissionIndex}" varStatus="status"> 
						
						 	<div class="form-group aMissionDiv">			
						 		<sf:hidden path="tasks[${status.index}]"/>
								<label for="cardMission${status.index+1}" class="col-sm-2 control-label">Mission ${status.index+1}</label>
								<div class="col-sm-5">
									<sf:textarea path="tasks[${status.index}].text" id="cardMission${status.index+1}" class="form-control" rows="3"/>
									<sf:errors path="tasks[${status.index}].text" cssClass="error"/>
								</div>
							<c:if test="${ status.last }">
								<div id="missionBtnDiv" class="col-sm-2 btn-group-vertical">
									<button type="button" id="delMissionBtn" class="btn btn-default btn-sm" <c:if test="${lastMissionIndex eq 0}">disabled="disabled"</c:if>>Delete</button>
									<button type="button" id="addMissionBtn" class="btn btn-default btn-sm">Add</button>
								</div>
							</c:if>
							</div>
					</c:forEach>
				</div>
				<div id="testDiv">
				</div>
				<div class="form-group">
					<label for="cardOutro" class="col-sm-2 control-label">Completion message</label>
					<div class="col-sm-5">
						<sf:textarea path="outro" id="cardOutro" class="form-control" rows="3"/>
						<sf:errors path="outro" cssClass="error"/>
					</div>
				</div>	
				
				<sf:hidden path="cardId"/>
				<input type="submit" name="submitBtn" value="Save card" class="btn btn-primary" />
			</sf:form>
			<c:out value="${status}"/>
		</div><!-- /.col-xs-12 main -->	
	</div><!--/.row-->
</div><!-- container -->
<jsp:include page="../fragments/footer.jsp"/>
</body>
	<script src="<c:url value="/resources/card-game/js/edit.js" />" type="text/javascript"></script>
</html>
