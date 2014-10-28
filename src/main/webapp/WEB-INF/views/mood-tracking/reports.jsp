<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>

<html>
<head>

    <title>Reports</title>
   
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

<div class="col-xs-12 col-sm-9">
  


<!--  <h1>Reports</h1> -->

 


<form:form action="" method="POST" name="command">
	
   		<div class="panel panel-default">
    	<div class="panel-heading"><h3 class="panel-title">Reports</h3></div> <!-- panel-heading -->
    	<div class="bs-example">
    	
	    <s:hasBindErrors name="command">
	      <div id="div_global_error" align="center">
	          <div id="global_errors">
	              <s:bind path="command">
	                  <c:forEach items="${status.errorMessages}" var="err">
	                      <c:out value='${err}'/><br/>
	                  </c:forEach>
	              </s:bind>
	          </div>
	      </div>
	  </s:hasBindErrors>
        <table class="table">
        
            <tr><td>Start Date</td>
            
                <td><form:input path="startDate" type="text" id="startDate"/> </td>
                
                <td><form:errors path="startDate" cssClass="error"/>
                
            

            <td>End Date</td>
                <td><form:input path="endDate" type="text" id="endDate"/></td>
                <td><form:errors path="endDate" cssClass="error"/></td>
            </tr>
        </table>
        
		
        <p align="center">
            <input class="btn btn-default" type="submit" id="save" name="save" value="Generate Report"/>
        </p>
    
    </div>
    </div>
</form:form>

<div>
    <div>

        <table border="1" class="table table-striped" id="table-large-columns" data-height="400" data-show-columns="true">
         <thead>
            <tr>

	                <th class="col-md-2">Date</th>
	                <th class="col-md-2">Rate Your Mood</th>
	              	<th class="col-md-2">How well you coped with your task today</th>
	              	<th class="col-md-2">How many hours did you sleep today (Hours)</th>
	                <th class="col-md-2">How long did you exercise today (Hours)</th>
	                <th class="col-md-2"> Interested</th>
	                <th class="col-md-2"> Irritable</th>
	                <th class="col-md-2"> Distressed</th>
	                <th class="col-md-2"> Alert</th>
	                <th class="col-md-2"> Excited</th>
	                <th class="col-md-2"> Ashamed</th>
	                <th class="col-md-2"> Upset</th>
	                <th class="col-md-2"> Inpired</th>
	                <th class="col-md-2"> Strong</th>
	                <th class="col-md-2"> Nervous</th>
	                <th class="col-md-2"> Guilty</th>
	                <th class="col-md-2"> Determined</th>
	                <th class="col-md-2"> Scared</th>
	                <th class="col-md-2"> Attentive</th>
	                <th class="col-md-2"> Hostile</th>
	                <th class="col-md-2"> Jittery</th>
	                <th class="col-md-2"> Enthusiastic</th>
	                <th class="col-md-2"> Active</th>
	                <th class="col-md-2"> Proud</th>
	                <th class="col-md-2"> Afraid</th>

            </tr>
            </thead>
            <tbody>
            
              <c:forEach var="_data" items="${dataItems}">
                <tr>
               		 <td class="col-md-6">${_data.date}</td>
               		 <td>${_data.moodRating}</td>
                    <td>${_data.copedWithTask}</td>
                    <td>${_data.hoursOfSleeping}</td>
                    <td>${_data.exerciseHours}</td>
                    <td>${_data.interested}</td>
                    <td>${_data.irritable}</td>
                    <td>${_data.distressed}</td>
                    <td>${_data.alert}</td>
                    <td>${_data.excited}</td>
                    <td>${_data.ashamed}</td>
                    <td>${_data.upset}</td>
                    <td>${_data.inspired}</td>
                    <td>${_data.strong}</td>
                    <td>${_data.nervous}</td>
                    <td>${_data.guilty}</td>
                    <td>${_data.determined}</td>
                    <td>${_data.scared}</td>
                    <td>${_data.attentive}</td>
                    <td>${_data.hostile}</td>
                    <td>${_data.jittery}</td>
                    <td>${_data.enthusiastic}</td>
                    <td>${_data.active}</td>
                    <td>${_data.proud}</td>
                    <td>${_data.afraid}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <script>
		   		 $(function () {
		        $('#large-columns-table').next().click(function () {
		            $(this).hide();
		            buildTable($('#table-large-columns'), 30, 30);
		        });
		    });
		</script>
    </div>
</div>
</div>

</body>
</html>