<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <head>
        <title>Spring Security Form Login Tutorial</title>
    </head>
     
    <body>
        <H1>Welcome to the Spring Security Form Login Tutorial!</H1>
        <form id="form" action="<c:url value='/j_spring_security_check' />" method="POST">

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

			Username:<br>
            <input type="text" name="username" value=""/><br><br>
            Password:<br>
            <input type="password" name="password" value=""/>
             
            <input value="Login" name="submit" type="submit"/>
        </form>
    </body>
</html>