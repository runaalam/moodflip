<%@ include file="/WEB-INF/views/include.jsp"%>

 <link rel="stylesheet" href="<c:url value="/resources/shared/css/sticky-footer.css" />">
 
<div class="panel-footer">
	<p>Copyright &copy; 2014 <fmt:message key="heading" /></p>
</div> 
<script>
$(document).ready(function() {
	// keep side bar component open while user is browsing that component's pages
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
	// show users points in navbar right
	$.ajax({
		  url: '/moodflip/card-game/points',
		  dataType: "text",
		  timeout: 5000,
		  // code to run regardless of success or failure
		  complete: function( xhr, status ) {
			  	if (status === "success"){
				    $('#user_points').prepend(xhr.responseText + " ");
			  	}else{
			  		console.log("couldn't find points");
			  	}
		  }
	  });
	
});
</script>

<%@ include file="/WEB-INF/views/angularjs/include-js.jsp"%>
