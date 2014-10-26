<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
	<head>
    	<title><sitemesh:write property='title', default='Moodflip'/></title>	
		<jsp:include page="fragments/headTag.jsp"/>
		<sitemesh:write property='head'/>
	</head>

	<body> 
		<div class="container">
			<div class="row">
				<jsp:include page="fragments/bodyHeader.jsp"/>
				<jsp:include page="fragments/bodySideBar.jsp"/>	
    			<sitemesh:write property='body'/>
			</div>
		</div>
		<jsp:include page="fragments/footer.jsp"/>
	</body>
</html>
