<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
});
</script>

