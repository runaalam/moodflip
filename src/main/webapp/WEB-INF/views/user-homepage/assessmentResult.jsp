<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
	<head>
	    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
	    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	    <script type="text/javascript">
	    //alert("start");
	    
	    google.load('visualization', '1', {'packages':['corechart']});
	    
	    // Set a callback to run when the Google Visualization API is loaded.
	    google.setOnLoadCallback(prepareChart);
	    
	    function drawLineChart(dates, scores, div) {
			//alert("in function draw");
	    	//alert(dates);
	    	//alert(scores);
	    	
	    	var data = new google.visualization.DataTable();
	
	    	data.addColumn('string', 'Dates');
	    	data.addColumn('number', 'Depression Scores');
	    	
	        var rows = dates.length;
	        data.addRows(rows);
	               
	        for (var i=0; i < rows; i++) {            
	        	for (var j=0; j < 2; j++) {                           
	        		if(j == 0) {
	            		data.setCell(i,j, dates[i]);
	                } else {
	                    data.setCell(i,j, scores[i]);
	                }
	        	}
	        }      	
	        var options = {
	            'title':'Assessment History Chart',
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
	
	    	drawLineChart(array.dates, array.scores,'chart_div');
	    }
	    
	    </script>
		<title><fmt:message key="userHomepage.assessment.results"/></title>	    
	</head>
	<body>
		<!-- include page header -->
		<div class="col-xs-12 col-sm-9">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title"><fmt:message key="userHomepage.assessment.results"/></h3>
					</div>
					<div class="panel-body">
						<c:choose>
							<c:when test="${not empty assessment}">
								<table class="table">
									<tr>
										<td>Assessment date:</td>
										<td><c:out value="${assessment.date}" /></td>
									</tr>
									<tr>
										<td>Assessment score:</td>
										<td><c:out value="${assessment.score}" />/60</td>
									</tr>
									<tr>
										<td>Depression scale:</td>
										<td><c:out value="${assessment.depressionScale}" /></td>
									</tr>
									<tr>
										<td>Sadness:</td>
										<td><c:out value="${assessment.resultDetails.dysphoria}" /></td>
									</tr>
									<tr>
										<td>Loss of Interest:</td>
										<td><c:out value="${assessment.resultDetails.anhedonia}" /></td>
									</tr>
									<tr>
										<td>Appetite:</td>
										<td><c:out value="${assessment.resultDetails.appetite}" /></td>
									</tr>
									<tr>
										<td>Sleep:</td>
										<td><c:out value="${assessment.resultDetails.sleep}" /></td>
									</tr>
									<tr>
										<td>Thinking:</td>
										<td><c:out
												value="${assessment.resultDetails.concentration}" /></td>
									</tr>
									<tr>
										<td>Guilt:</td>
										<td><c:out value="${assessment.resultDetails.guilt}" /></td>
									</tr>
									<tr>
										<td>Fatigue:</td>
										<td><c:out value="${assessment.resultDetails.fatigue}" /></td>
									</tr>
									<tr>
										<td>Movement:</td>
										<td><c:out value="${assessment.resultDetails.agitation}" /></td>
									</tr>
									<tr>
										<td>Suicidal:</td>
										<td><c:out
												value="${assessment.resultDetails.suicidalIdeation}" /></td>
									</tr>
								</table>
								<br>
								<br>
								<br>
								<div id="chart_div"></div>
							</c:when>
							<c:when test="${empty assessment}"><fmt:message key="userHomepage.assessment.empty"/></br>
		             				<a
									href="<c:url value="/user-homepage/depression-assessment"/>">
									<fmt:message key="userHomepage.assessment.start"/></a> 
		             			</c:when>
						</c:choose>
					</div>
				</div> <!-- panel-default -->
				<br>
				<br>
			</div> <!-- panel-group -->
		</div> <!-- col-xs-12 main -->
	</body>
</html>