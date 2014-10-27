<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
    <title>Charts</title>

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
    google.setOnLoadCallback(prepareLineChart);
    google.setOnLoadCallback(prepareBarChart);
   // google.setOnLoadCallback(prepareChart1);
    // Line Chart

   function drawLineChart(dates, moodRating, copedWithTask, div) {
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
        	line_chart_div.innerHTML = '<img src="' + chart.getImageURI() + '">';
          
        });
    	
    	chart.draw(data, {width: 800, height: 600, title: ' ',hAxis: {title: 'Dates', titleTextStyle: {color: 'red'}}});
    }


    function prepareLineChart() {
		//alert("button clicked");
    	var array;

    	var data = $.ajax({
            url: "charts/drawLineChart",
    	          dataType:"json",
    	          async: false,
    	          error: function( jqXHR, textStatus,errorThrown ){
    	        	  alert('req failed ' + errorThrown);
    	        	
    	          }
    	          }).responseText;

    	//alert(data);
    	array = $.parseJSON(data);
    	//alert(array.dates);
    	//alert(array.moodRating);

    	drawLineChart(array.dates, array.moodRating, array.copedWithTask,'line_chart_div');
   }
    //END LINE CHART
    

    //COLUMN CHART
  
       function drawBarChart(dates, hoursOfSleeping, div) {
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
        	bar_chart_div.innerHTML = '<img src="' + chart1.getImageURI() + '">';
          console.log(div.innerHTML);
        });
    	chart1.draw(data, {width: 500, height: 600, title: ' ',hAxis: {title: 'Dates', titleTextStyle: {color: 'red'}}});
    }
 
    function prepareBarChart() {
		//alert("button clicked");
    	var array;
    	   
    	var data = $.ajax({
            url: "charts/drawColumnChart",
    	          dataType:"json",
    	          async: false
    	          }).responseText;

    	//alert(data);
    	array = $.parseJSON(data);
    	//alert(array.dates);
    	//alert(array.hoursOfSleeping);


    	drawBarChart(array.dates, array.hoursOfSleeping,'bar_chart_div');
    }

       
   //end Column chart      
       
  
      
 
    

    </script>
</head>
  <body>
	<div class="col-xs-12 col-sm-9">
  	<div class="panel panel-default">
    	<div class="panel-heading"><h3 class="panel-title">Charts</h3></div> <!-- panel-heading -->
    	<div class="bs-example">

  <form:form action="" method="POST" name="command">
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
  
    <div>
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
            <input class="btn btn-default" type="submit" id="save" name="save" value="Generate Chart"/>
      </p>
        
        
    </div>
</form:form>

<!--Div that will hold the line chart-->
  <c:if test="${drawGraph == true}">
  
	   
	          <div id="line_chart_div" ></div>
         		<div id="bar_chart_div" ></div>
     	
   
	</c:if> 
   <%--<div id="line_chart_div" style="float: left"></div>--%>
  <%--<div id="bar_chart_div" style="float: right"></div>--%>
  <%--<div id="chart_div"></div>--%>
  <!--Div that will hold the column chart-->
   <!-- <div id="chart_div1" style="width:600; height:500"
    style="float: left"
    style="float: right"></div>-->
    
    
    </div>
 </div>
 </div>
  </body>
</html>