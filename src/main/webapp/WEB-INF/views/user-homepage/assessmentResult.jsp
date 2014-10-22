<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
</head>
	<jsp:include page="../fragments/headTag.jsp"/>
<body> 
<!-- include page header -->
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="container">
	<div class="row">
    	<!-- include Sidebar --> 	
	    <jsp:include page="../fragments/bodySideBar.jsp"/>
	    <div class="col-xs-12 col-sm-9">
	    	<div class="panel-group" id="accordion">
        		<div class="panel panel-default">
            		<div class="panel-heading">
                		<h3 class="panel-title">Assessment Result </h3>
            		</div>
                	<div class="panel-body">
                   		<table class="table">
	                   		<tr><td>Id</td>
								<td>Score</td>
								<td>Depression Scale</td>
								<td>Date</td>
							</tr>
                   		
							<c:choose>
								<c:when test="${!empty assList}">
									<c:forEach items="${assList}" var="assessment">
                               			<tr><td><c:out value="${assessment.id}"/></td> 
                               				<td><c:out value="${assessment.score}"/></td> 
                               				<td><c:out value="${assessment.depressionScale}"/></td>  
	                                        <td><c:out value="${assessment.date}" /></td>  
                              			 </tr>
                       				</c:forEach>
								</c:when>
								<c:when test="${empty assList}">There is no assessment result.</c:when>
							</c:choose>
						</table>
					</div>
				</div><!-- panel-default -->
				</br></br>			
									<div id="chart_div"></div>		
			</div><!-- panel-group -->			
	    </div><!-- col-xs-12 main -->
	</div><!-- row -->
</div><!-- container -->

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>