<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Express Your Mood</title>
</head>
<body>



	<div class="row">
		<div class="col-sm-4 col-md-4">
			<div class="thumbnail">
				<img src="<c:url value="/resources/images/featureupdate.jpg"/>" />
				<div class="caption">
					<h3 class="text-center">Core Data</h3>
					<p>Update your mood when ever you want. It is easy, and can
						help make you happier.</p>
				</div>
			</div>
		</div>
		
		<div class="col-sm-4 col-md-4">
			<div class="thumbnail">
				<img src="<c:url value="/resources/images/featuregraph.jpg/"/>" />
				<div class="caption">
					<h3 class="text-center">Charts & Reports</h3>
					<p>View your mood history over weeks and months. Get the bigger
						picture on your happiness.</p>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
