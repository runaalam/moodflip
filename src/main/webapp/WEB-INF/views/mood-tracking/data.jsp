<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ExpressYourMood</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
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

<div class="container">
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
  
<script>
    $(function () {
        $("#date").datepicker({ dateFormat: 'dd/mm/yy'});
    });
</script>
<h3><a href="<c:url value="/mood-tracking"/>">Express Your Mood</a></h3>


<h1>Data</h1>



<div>
<c:if test="${msg!=null}">
    <div>${msg}</div>
</c:if>

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

<form:form action="" method="POST" name="command">
    <div class="nav">
    <ul class="nav nav-pills nav-stacked">
    
        <table>
            <tr>Date
                <form:input path="date" type="text" id="date"/>
                <form:errors path="date" cssClass="error"/>
            </tr>
			</div>
			
			<div>
            <tr>
                <th>Rate Your Mood</th>
                <td>
                    <form:select path="moodRating" items="${command.moodRatingValues}"/>
                    <form:errors path="moodRating" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <th>How well you coped with your task today</th>
                <td>
                    <form:select path="copedWithTask" items="${command.copedWithTaskValues}"/>
                    <form:errors path="copedWithTask" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <th>How many hours did you sleep today</th>
                <td>
                    <form:select path="hoursOfSleeping" items="${command.hoursOfSleepingValues}"/>
                    <form:errors path="hoursOfSleeping" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <th>How long did you exercise today</th>
                <td>
                    <form:select path="exerciseHours" items="${command.exerciseHoursValues}"/>
                    <form:errors path="exerciseHours" cssClass="error"/>
                </td>
            </tr>
        </table>
    </div>
    <div>
        <table>
            <tr>
                <td>
                    Interested
                    <form:select path="interested" items="${command.interestedValues}"/>
                    <form:errors path="interested" cssClass="error"/>
                </td>
                <td>
                    Irritable
                    <form:select path="irritable" items="${command.irritableValues}"/>
                    <form:errors path="irritable" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Distressed
                    <form:select path="distressed" items="${command.distressedValues}"/>
                    <form:errors path="distressed" cssClass="error"/>
                </td>
                <td>
                    Alert
                    <form:select path="alert" items="${command.alertValues}"/>
                    <form:errors path="alert" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Excited
                    <form:select path="excited" items="${command.excitedValues}"/>
                    <form:errors path="excited" cssClass="error"/>
                </td>
                <td>
                    Ashamed
                    <form:select path="ashamed" items="${command.ashamedValues}"/>
                    <form:errors path="ashamed" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Upset
                    <form:select path="upset" items="${command.upsetValues}"/>
                    <form:errors path="upset" cssClass="error"/>
                </td>
                <td>
                    Inpired
                    <form:select path="inspired" items="${command.inspiredValues}"/>
                    <form:errors path="inspired" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Strong
                    <form:select path="strong" items="${command.strongValues}"/>
                    <form:errors path="strong" cssClass="error"/>
                </td>
                <td>
                    Nervous
                    <form:select path="nervous" items="${command.nervousValues}"/>
                    <form:errors path="nervous" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Guilty
                    <form:select path="guilty" items="${command.guiltyValues}"/>
                    <form:errors path="guilty" cssClass="error"/>
                </td>
                <td>
                    Determined
                    <form:select path="determined" items="${command.determinedValues}"/>
                    <form:errors path="determined" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Scared
                    <form:select path="scared" items="${command.scaredValues}"/>
                    <form:errors path="scared" cssClass="error"/>
                </td>
                <td>
                    Attentive
                    <form:select path="attentive" items="${command.attentiveValues}"/>
                    <form:errors path="attentive" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Hostile
                    <form:select path="hostile" items="${command.hostileValues}"/>
                    <form:errors path="hostile" cssClass="error"/>
                </td>
                <td>
                    Jittery
                    <form:select path="jittery" items="${command.jitteryValues}"/>
                    <form:errors path="jittery" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Enthusiastic
                    <form:select path="enthusiastic" items="${command.enthusiasticValues}"/>
                    <form:errors path="enthusiastic" cssClass="error"/>
                </td>
                <td>
                    Active
                    <form:select path="active" items="${command.activeValues}"/>
                    <form:errors path="active" cssClass="error"/>
                </td>
            </tr>

            <tr>
                <td>
                    Proud
                    <form:select path="proud" items="${command.proudValues}"/>
                    <form:errors path="proud" cssClass="error"/>
                </td>
                <td>
                    Afraid
                    <form:select path="afraid" items="${command.afraidValues}"/>
                    <form:errors path="afraid" cssClass="error"/>
                </td>
            </tr>
        </table>
    </div>
    <div>
        <input type="submit" id="save" name="save" value="Save"/>
    </div>

</form:form>


</div>

</body>
</html>