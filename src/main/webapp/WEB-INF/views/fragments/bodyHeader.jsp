<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<nav class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	    	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	    		<span class="icon-bar"></span>
	      	</button>
	      <a class="navbar-brand" href="<c:url value="/" />"><fmt:message key="heading" /></a>
	    </div>
	    
	    <p class="navbar-text navbar-right">100 pts Level 2</p>
	    <p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">User123</a></p>
	</div>
</nav>
      
