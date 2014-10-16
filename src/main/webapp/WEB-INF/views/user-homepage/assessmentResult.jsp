<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Assessment Result</title>
		
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    	<script type="text/javascript">
	      google.load("visualization", "1", {packages:["corechart"]});
	      google.setOnLoadCallback(drawChart);
	      function drawChart() {
	        var data = google.visualization.arrayToDataTable([
	          ['Date', 'Score'],
	          ['2004',  15],
	          ['2006',  20],
	          ['2007',  40],
	          ['2008',  60]
	        ]);
	
	        var options = {
	          title: 'Assessment score'
	        };
	
	        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	
	        chart.draw(data, options);
	      }
	    </script>
	</head>
	<body>
		<h1>Assessment Result</h1>
		<div id="chart_div" style="width: 800px; height: 400px;"></div>
	</body>
</html>