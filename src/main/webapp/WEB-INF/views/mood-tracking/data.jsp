<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>ExpressYourMood</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"/>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

    <script>
        $(function () {
            $("#date").datepicker({ dateFormat: 'dd/mm/yy'});
        });
    </script>
   

</head>

<body>
<div class="col-xs-12 col-sm-9">

	<c:if test="${msg!=null}">
	    <div>${msg}</div>
	</c:if>

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

	<form:form action="" method="POST" name="command">
   		<div class="panel panel-default">
			<div class="panel-heading"><h3 class="panel-title">Data</h3></div> <!-- panel-heading -->
  					<div class="bs-example">
			            <table class="table table-condensed">
			            
			                <tr><td>Date</td>
			                	<td><form:input path="date" type="text" id="date"/></td>
			                    <td><form:errors path="date" cssClass="error"/></td>
			                </tr>
			           
			            	<tr><td>Rate Your Mood</td>
			            		<td>
					                <form:select path="moodRating" items="${command.moodRatingValues}"/>
					                <form:errors path="moodRating" cssClass="error"/>
					            </td>
			            	</tr>
					        <tr>
					            <td>How well you coped with your task today</td>
					            <td>
					                <form:select path="copedWithTask" items="${command.copedWithTaskValues}"/>
					                <form:errors path="copedWithTask" cssClass="error"/>
					            </td>
					        </tr>
			
					        <tr>
					            <td>How many hours did you sleep today</td>
					            <td>
					                <form:select path="hoursOfSleeping" items="${command.hoursOfSleepingValues}"/>
					                <form:errors path="hoursOfSleeping" cssClass="error"/>
					            </td>
					        </tr>
			
					        <tr>
					            <td>How long did you exercise today</td>
					            <td>
					                <form:select path="exerciseHours" items="${command.exerciseHoursValues}"/>
					                <form:errors path="exerciseHours" cssClass="error"/>
					            </td>
					        </tr>
			       </table>
			       </div>
			       </div>
			      
			       <div class="panel panel-default">
					<div class="panel-heading"><h3 class="panel-title">Symptoms</h3></div> <!-- panel-heading -->
  					<div class="bs-example">
  					 <table class="table table-condensed">
			            <tr>
			                <td>Interested</td>
			                 <td><form:select path="interested" items="${command.interestedValues}"/>
			                    <form:errors path="interested" cssClass="error"/>
			                </td>
			                <td>
			                    Irritable </td>
			                    <td>
			                    <form:select path="irritable" items="${command.irritableValues}"/>
			                    <form:errors path="irritable" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Distressed </td>
			                    <td>
			                    <form:select path="distressed" items="${command.distressedValues}"/>
			                    <form:errors path="distressed" cssClass="error"/>
			                </td>
			                <td>
			                    Alert </td>
			                    <td>
			                    <form:select path="alert" items="${command.alertValues}"/>
			                    <form:errors path="alert" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Excited </td>
			                    <td>
			                    <form:select path="excited" items="${command.excitedValues}"/>
			                    <form:errors path="excited" cssClass="error"/>
			                </td>
			                <td>
			                    Ashamed </td>
			                    <td>
			                    <form:select path="ashamed" items="${command.ashamedValues}"/>
			                    <form:errors path="ashamed" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Upset </td>
			                    <td>
			                    <form:select path="upset" items="${command.upsetValues}"/>
			                    <form:errors path="upset" cssClass="error"/>
			                </td>
			                <td>
			                    Inpired </td>
			                    <td>
			                    <form:select path="inspired" items="${command.inspiredValues}"/>
			                    <form:errors path="inspired" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Strong </td>
			                    <td>
			                    <form:select path="strong" items="${command.strongValues}"/>
			                    <form:errors path="strong" cssClass="error"/>
			                </td>
			                <td>
			                    Nervous </td>
			                    <td>
			                    <form:select path="nervous" items="${command.nervousValues}"/>
			                    <form:errors path="nervous" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Guilty </td>
			                    <td>
			                    <form:select path="guilty" items="${command.guiltyValues}"/>
			                    <form:errors path="guilty" cssClass="error"/>
			                </td>
			                <td>
			                    Determined </td>
			                    <td>
			                    <form:select path="determined" items="${command.determinedValues}"/>
			                    <form:errors path="determined" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Scared </td>
			                    <td>
			                    <form:select path="scared" items="${command.scaredValues}"/>
			                    <form:errors path="scared" cssClass="error"/>
			                </td>
			                <td>
			                    Attentive </td>
			                    <td>
			                    <form:select path="attentive" items="${command.attentiveValues}"/>
			                    <form:errors path="attentive" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Hostile </td>
			                    <td>
			                    <form:select path="hostile" items="${command.hostileValues}"/>
			                    <form:errors path="hostile" cssClass="error"/>
			                </td>
			                <td>
			                    Jittery </td>
			                    <td>
			                    <form:select path="jittery" items="${command.jitteryValues}"/>
			                    <form:errors path="jittery" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Enthusiastic </td>
			                    <td>
			                    <form:select path="enthusiastic" items="${command.enthusiasticValues}"/>
			                    <form:errors path="enthusiastic" cssClass="error"/>
			                </td>
			                <td>
			                    Active </td>
			                    <td>
			                    <form:select path="active" items="${command.activeValues}"/>
			                    <form:errors path="active" cssClass="error"/>
			                </td>
			            </tr>
			
			            <tr>
			                <td>
			                    Proud </td>
			                    <td>
			                    <form:select path="proud" items="${command.proudValues}"/>
			                    <form:errors path="proud" cssClass="error"/>
			                </td>
			                <td>
			                    Afraid </td>
			                    <td>
			                    <form:select path="afraid" items="${command.afraidValues}"/>
			                    <form:errors path="afraid" cssClass="error"/>
			                </td>
			            </tr>
			        </table>
			        </div> <!--form-group  -->
					</div> <!-- panel panel-default -->
					
	    <p align="center">
	        <input class="btn btn-primary btn-lg" type="submit" id="save" name="save" value="Save"/>
	   </p>
	    
		
		
	</form:form>
</div> <!-- col-xs-12 col-sm-9 -->
</body>
</html>