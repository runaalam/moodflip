<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="<c:url value="cardGame.htm"/>">card game</a>
<a href="<c:url value="moodTracking.htm"/>">mood tracking system</a>
<a href="<c:url value="communication.htm"/>">communication system</a>
<a href="<c:url value="personalisation.htm"/>">personalisation for different user classes</a>
<a href="<c:url value="userHomepage.htm"/>">user homepage, status, activity, survey</a>

</body>
</html>
