<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script>
    $(function () {
        $("#date").datepicker({ dateFormat: 'dd/mm/yy'});
    });
</script>
 			<tr>Date
             <form:input path= "date" type="text" id="date"/>
            </tr>
 <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Dates', 'Mood', 'Coped'],
          ['2004',  10,      4],
          ['2005',  2,      4],
          ['2006',  0,       10],
          ['2007',  10,      5]
        ]);

        var options = {
          //title: 'Charts'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script>
    $(function () {
        $("#date").datepicker({ dateFormat: 'dd/mm/yy'});
    });
</script>
    <div id="chart_div" style="width: 900px; height: 500px;"></div>
  </body>
</html>