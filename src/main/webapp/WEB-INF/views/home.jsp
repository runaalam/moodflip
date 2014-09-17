<%@ include file="/WEB-INF/views/include.jsp"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>

	<h3><a href="<c:url value="/card-game"/>">Card Game</a></h3>

	<h3><a href="<c:url value="/mood-tracking"/>">Express Your Mood</a></h3>
	
	<h3><a href="<c:url value="/forum" />">Forum</a></h3>

	<h3><a href="<c:url value="/personalisation"/>">personalisation for different user classes</a></h3>

	<h3><a href="<c:url value="/user-homepage"/>">user homepage, status, activity, survey</a></h3>
	
</body>
</html>
