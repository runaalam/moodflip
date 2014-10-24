<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>

    
    <title>Charts</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

    <script type="text/javascript">
    //alert("start");
    
    google.load('visualization', '1', {'packages':['corechart']});
    
    //Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(prepareChart);
    
    function drawLineChart(dates, moodRating, div) {
//		alert("in function draw");

//    	alert(dates);
//    	alert(moodRating);
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
    	chart.draw(data, options);
    }
 
    function prepareChart() {
		//alert("button clicked");
    	var array;
    	   
    	var data = $.ajax({
            url: "drawLineChart",
    	          dataType:"json",
    	          async: false
    	          }).responseText;

    	//alert(data);
    	array = $.parseJSON(data);
    	//alert(array.dates);
    	//alert(array.scores);

    	drawLineChart(array.dates, array.moodRating,'chart_div');
    }

        $(function () {
            $("#startDate").datepicker({ dateFormat: 'dd/mm/yy'});
            $("#endDate").datepicker({ dateFormat: 'dd/mm/yy'});
        });
    </script>

</head>
  <body>

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
              <a class="navbar-brand" href="#">MoodFlip</a>
          </div>

          <p class="navbar-text navbar-right"><c:out value="${globalPoints}"/> pts Level 2</p>
          <p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">User123</a></p>
      </div>
  </nav>

  <form:form action="" method="POST" name="command">
    <div>
        <table>
            <tr>Start Date
                <form:input path="startDate" type="text" id="startDate" />
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

  <div id="chart_div"></div>
<!--Div that will hold the line chart-->
    <%--<div id="chart_div" style="width:400; height:300"></div>--%>
    
    
 
  </body>
</html>