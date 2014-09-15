<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
  	<h1><fmt:message key="heading"/></h1>
  	<p><fmt:message key="greeting"/> <c:out value="${model.now} }"/></p>
  	<h3>Products</h3>
  	<c:forEach items="${model.products}" var="prod">
  		<c:out value="${prod.description }"/> 
  		<i>$<c:out value="${prod.price}"/></i>
  		<a href="product/edit/${prod.id}">edit</a>
  		<a href="product/delete/${prod.id}">delete</a>
  		<br><br>
  	</c:forEach>
  	
  	<!--  link to the increase price page -->
  	<br>
  		<a href="<c:url value="priceincrease.htm"/>">Increase Prices</a>	
  	<br>
  </body>
</html>