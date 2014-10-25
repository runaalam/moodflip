<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>

    
    <title>Charts</title>
  
    
    <s:hasBindErrors name="command">
    <div id="div_global_error" align="center">
        <h1>
            Error!!
        </h1>

        <div id="global_errors">
            <s:bind path="command">
                <ul class="header-list">
                    <c:forEach items="${status.errorMessages}" var="err">
                        <li>
                            <c:out value='${err}'/>
                        </li>
                    </c:forEach>
                </ul>
            </s:bind>
        </div>
    </div>
	</s:hasBindErrors>
	
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	

	
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
   
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

    <script type="text/javascript">
    
    $(function () {
        $("#startDate").datepicker({ dateFormat: 'dd/mm/yy'});
        $("#endDate").datepicker({ dateFormat: 'dd/mm/yy'});
    });
    
    //alert("start");
    // LINE CHART
    google.load('visualization', '1', {'packages':['corechart']});
   //google.load('visualization', '2', {'packages':['corechart']});
   // google.load('visualization', '3', {'packages':['corechart']});
    
    //Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(prepareChart);
   // google.setOnLoadCallback(prepareChart1);
    // Line Chart 
/*   function drawLineChart(dates, moodRating, copedWithTask, div) {
//		alert("in function draw");

//    	alert(dates);
//    	alert(moodRating);
    	var data = new google.visualization.DataTable();

    	data.addColumn('string', 'Dates');
    	data.addColumn('number', 'Mood');
    	data.addColumn('number', 'Coped with Task');
    	
        var rows = dates.length;
        data.addRows(rows);
               
        for (var i=0; i < rows; i++) {            
        	for (var j=0; j < 3; j++) {                           
        		if(j == 0) {
            		data.setCell(i,j, dates[i]);
                } else if (j == 1) {
                    data.setCell(i,j, moodRating[i]);
                } else { data.setCell(i,j, copedWithTask[i])
                	
                }
        	}
        }      	
        var options = {
            'title':' ',
            legend: { position: 'bottom' }
        };

    	var chart = new google.visualization.LineChart(document.getElementById(div));
    	
    	 // Wait for the chart to finish drawing before calling the getImageURI() method.
        google.visualization.events.addListener(chart, 'ready', function () {
          chart_div.innerHTML = '<img src="' + chart.getImageURI() + '">';
          console.log(chart_div.innerHTML);
        });
    	
    	chart.draw(data, {width: 800, height: 600, title: ' ',hAxis: {title: 'Dates', titleTextStyle: {color: 'red'}}});
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
    	//alert(array.moodRating);

    	drawLineChart(array.dates, array.moodRating, array.copedWithTask,'chart_div');
   } 
    //END LINE CHART
    
   */  
    
    //COLUMN CHART
  
       function drawColumnChart(dates, hoursOfSleeping, div) {
//		alert("in function draw");

//    	alert(dates);
//    	alert(hoursOfSleeping);
    	var data = new google.visualization.DataTable();

    	data.addColumn('string', 'Dates');
    	data.addColumn('number', 'Sleep');
    	
        var rows = dates.length;
        data.addRows(rows);
               
        for (var i=0; i < rows; i++) {            
        	for (var j=0; j < 2; j++) {                           
        		if(j == 0) {
            		data.setCell(i,j, dates[i]);
                } else {
                    data.setCell(i,j, hoursOfSleeping[i]);
                }
        	}
        }      	
        var options = {
            'title':'Chart',
            legend: { position: 'bottom' }
        };

    	var chart1 = new google.visualization.ColumnChart(document.getElementById(div));
    	
    	// Wait for the chart to finish drawing before calling the getImageURI() method.
        google.visualization.events.addListener(chart1, 'ready', function () {
          chart_div.innerHTML = '<img src="' + chart1.getImageURI() + '">';
          console.log(chart_div.innerHTML);
        });
    	chart1.draw(data, {width: 500, height: 600, title: ' ',hAxis: {title: 'Dates', titleTextStyle: {color: 'red'}}});
    }
 
    function prepareChart() {
		//alert("button clicked");
    	var array;
    	   
    	var data = $.ajax({
            url: "drawColumnChart",
    	          dataType:"json",
    	          async: false
    	          }).responseText;

    	//alert(data);
    	array = $.parseJSON(data);
    	//alert(array.dates);
    	//alert(array.hoursOfSleeping);

    	drawColumnChart(array.dates, array.hoursOfSleeping,'chart_div');
    } 

       
   //end Column chart      
       
  
      
 
    

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
  
  <h3><a href="<c:url value="/mood-tracking"/>">Express Your Mood</a></h3>
  <h1>Charts</h1>

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

<!--Div that will hold the line chart-->
   <div id="chart_div"></div>
<!--Div that will hold the column chart-->
   <!-- <div id="chart_div1" style="width:600; height:500"></div>-->
    
    
    
 
  </body>
</html>