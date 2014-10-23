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
     
    
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
    //alert("start");
    
    google.load('visualization', '1', {'packages':['corechart']});
    
    //Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(prepareChart);
    
    function drawLineChart(dates, moodRating, div) {
		//alert("in function draw");

    	//alert(dates);
    	//alert(scores);
    	var data = new google.visualization.DataTable();

    	data.addColumn('string', 'Dates');
    	data.addColumn('number', 'Mood');
    	
        var rows = dates.length;
        data.addRows(rows);
               
        for (var i=0; i < rows; i++) {            
        	for (var j=0; j < 2; j++) {                           
        		if(j == 0) {
            		data.setCell(i,j, dates[i]);
                } else {
                    data.setCell(i,j, moodRating[i]);
                }
        	}
        }      	
        var options = {
            'title':'Chart',
            legend: { position: 'bottom' }
        };

    	var chart = new google.visualization.LineChart(document.getElementById(div));
    	chart.draw(view, options);
    }
 
    function prepareChart() {
		//alert("button clicked");
    	var array;
    	   
    	var data = $.ajax({
    	          url: <c:url value="/drawLineChart"/>"
    	          dataType:"json",
    	          async: false
    	          }).responseText;

    	//alert(data);
    	array = $.parseJSON(data);
    	//alert(array.dates);
    	//alert(array.scores);

    	drawLineChart(array.dates, array.moodRating,'chart_div');
    }
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
<!--Div that will hold the line chart-->
    <div id="chart_div" style="width:400; height:300"></div>
    
    
 
  </body>
</html>