<%@ include file="/WEB-INF/views/include.jsp"%>
<html ng-app="moodFlip">
<head>
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/comm/css/forums.css" />">
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</head>
<body>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<div class="container">
	
	<ol class="breadcrumb">
  		<li class="active">Forums</li>
	</ol>
	
	<div class="row">
	<div class="col-md-8">
	<h1>Forums</h1>
	</div>
	</div>
	
	<div class="row">
	<div class="col-md-12">
	
	<c:choose>
	<c:when test="${!empty forums}">
		<c:forEach items="${forums}" var="forum">
		<div class="row forum">
		<div class="col-md-10">
			<p class="title"><a href="<c:url value="/forums/${forum.id}" />" ><c:out value="${forum.forumName}" /></a></p>
		</div>
		
		<div class="col-md-2">
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="<c:url value="/forums/edit/${forum.id}"/>"><button type="button" class="btn btn-default">Edit</button></a>
				<a href="<c:url value="/forums/delete/${forum.id}"/>"><button type="button" class="btn btn-danger">Delete</button></a>
			</sec:authorize>
		</div>
		
		</div>
		</c:forEach>
	</c:when>
	<c:when test="${empty forums}">
	<div class="row">
	<div class="col-md-12">
	<p>There are no forums.</p>
	</div>
	</div>
	</c:when>
	</c:choose>
	
	</div>
	</div>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div class="row">
	<div class="col-md-12">
	<div class="pull-right forum-btn">
	<a href="<c:url value="/forums/create"/>"><button type="button" class="btn btn-primary btn-lg">New Forum</button></a>
	</div>
	</div>
	</div>
	</sec:authorize>
	
	</div>

	<%@ include file="/WEB-INF/views/fragments/footer.jsp"%>
</body>
</html>