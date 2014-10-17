<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
    <title>Charts</title>
     <%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"/>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#startDate").datepicker({ dateFormat: 'dd/mm/yy'});
            $("#endDate").datepicker({ dateFormat: 'dd/mm/yy'});
        });
    </script>

  </head>
  <body>
  <form:form action="" method="POST" name="command">
    <div>
        <table>
            <tr>Start Date
                <form:input path="startDate" type="text" id="startDate"/>
                <form:errors path="startDate" cssClass="error"/>
            </tr>

            <tr>End Date
                <form:input path="endDate" type="text" id="endDate"/>
                <form:errors path="endDate" cssClass="error"/>
            </tr>
        </table>

        <div>
            <input type="submit" id="save" name="save" value="Generate Chart"/>
        </div>
    </div>
</form:form>
 
  </body>
</html>