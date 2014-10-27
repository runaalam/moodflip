<%@ include file="/WEB-INF/views/include.jsp"%>

 <link rel="stylesheet" href="<c:url value="/resources/shared/css/sticky-footer.css" />">
 
<div class="panel-footer">
	<p>Copyright &copy; 2014 <fmt:message key="heading" /></p>
</div> 
<script>
$(document).ready(function() {
	var pathname = window.location.pathname; 
	if (pathname.match("card-game")){
		$("#collapseTwo").addClass("in");
	}
	if (pathname.match("user-homepage")){
		$("#collapseOne").addClass("in");
	}
	if (pathname.match("mood-tracking")){
		$("#collapseThree").addClass("in");
	}
});
</script>

<%@ include file="/WEB-INF/views/angularjs/include-js.jsp"%>
