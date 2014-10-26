<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<html ng-app="moodFlip">
<head>
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/comm/css/forums.css" />">
</head>
<body>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<div class="container">
	
	<ol class="breadcrumb">
		<li><a href="<c:url value="/forums" />">Forums</a></li>
  		<li class="active">${forum.forumName}</li>
	</ol>
	
	<c:url value="/forums/${forum.id}" var="pagedLink">
		<c:param name="p" value="~" />
	</c:url>
	
	<div class="row">
	<div class="col-md-8">
	<h1>${forum.forumName}</h1>
	</div>
	<div class="col-md-4">
	<div class="pull-right forum-btn">
	<a href="<c:url value="/forums/${forum.id}/newTopic"/>"><button type="button" class="btn btn-primary btn-lg">New Topic</button></a>
	</div>
	</div>
	</div>
	
	<div class="row">
	<div class="col-md-12">

	<c:choose>
	<c:when test="${!empty pagedListHolder.pageList}">
	<c:forEach items="${pagedListHolder.pageList}" var="topic">
	
		<div class="row topic">
		<div class="col-md-12">
	
		<div class="row">
		
		<div class="col-md-12">
		<h3><a href="<c:url value="/forums/topic/${topic.id}"/>" class="newLineOverflow" ><c:out value="${topic.name}" /></a></h3>
		</div>
		
		</div>
		
		<div class="row topic-detail">
		<div class="col-md-12">
		<table class="table table-condensed">
			<tr>
				<th>Created</th>
				<th>Edited</th>
				<th>Posted By</th>
				<th>Last Post</th>
				<th>Views</th>
				<th>Replies</th>
			</tr>
			
				<tr>
					<td><c:out value="${topic.createdAt}" /></td>
					<td><c:out value="${topic.editedAt}" /></td>
					<td><c:out value="${topic.user.username}" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
					<td><c:out value="" /></td>
				</tr>
			
		</table>
		</div>
		</div>
		
		</div>
		</div>
		</c:forEach>
	</c:when>
	<c:when test="${empty pagedListHolder.pageList}">
	<div class="row">
	<div class="col-md-12">
	<p>There are no topics or posts in this forum.</p>
	</div>
	</div>
	</c:when>
	</c:choose>
	
	</div>
	</div>

	<div class="row">
	<div class="col-md-8">
	<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
	</div>
	<div class="col-md-4">
	<div class="pull-right forum-btn">
	<a href="<c:url value="/forums/${forum.id}/newTopic"/>"><button type="button" class="btn btn-primary btn-lg">New Topic</button></a>
	</div>
	</div>
	</div>
	
	</div>

</body>
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
<%@ include file="/WEB-INF/views/angularjs/include-js.jsp"%>
</html>