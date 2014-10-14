
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
     <title>ExpressYourMood</title>
     <%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp"%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script>
    $(function () {
        $("#date").datepicker({ dateFormat: 'dd/mm/yy'});
    });
</script>

<h1>Data</h1>
<div>
<div>
    
            </div>
        </div>
 

<div> 
    <form:form action="" method="POST" name="command">
        <div>
            <table>
             <tr>Date
             <form:input path= "date" type="text" id="date"/>
            </tr>
            	
                <tr>
                    <th>Rate Your Mood</th>
                    <td>
                        <form:select path="moodRating" items="${dropDownValues}"/>
                        <form:errors path="moodRating" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <th>How well you coped with your task today</th>
                    <td>
                        <form:select path="copedWithTask" items="${dropDownValues}"/>
                        <form:errors path="copedWithTask" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <th>How many hours did you sleep today</th>
                    <td>
                        <form:select path="hoursOfSleeping" items="${dropDownValues}"/>
                        <form:errors path="hoursOfSleeping" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <th>How long did you exercise today</th>
                    <td>
                        <form:select path="exerciseHours" items="${dropDownValues}"/>
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
                        <form:select path="interested" items="${dropDownValues}"/>
                        <form:errors path="interested" cssClass="error"/>
                    </td>
                    <td>
                        Irritable
                        <form:select path="irritable" items="${dropDownValues}"/>
                        <form:errors path="irritable" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Distressed
                        <form:select path="distressed" items="${dropDownValues}"/>
                        <form:errors path="distressed" cssClass="error"/>
                    </td>
                    <td>
                        Alert
                        <form:select path="alert" items="${dropDownValues}"/>
                        <form:errors path="alert" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Excited
                        <form:select path="excited" items="${dropDownValues}"/>
                        <form:errors path="excited" cssClass="error"/>
                    </td>
                    <td>
                        Ashamed
                        <form:select path="ashamed" items="${dropDownValues}"/>
                        <form:errors path="ashamed" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Upset
                        <form:select path="upset" items="${dropDownValues}"/>
                        <form:errors path="upset" cssClass="error"/>
                    </td>
                    <td>
                        Inpired
                        <form:select path="inspired" items="${dropDownValues}"/>
                        <form:errors path="inspired" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Strong
                        <form:select path="strong" items="${dropDownValues}"/>
                        <form:errors path="strong" cssClass="error"/>
                    </td>
                    <td>
                        Nervous
                        <form:select path="nervous" items="${dropDownValues}"/>
                        <form:errors path="nervous" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Guilty
                        <form:select path="guilty" items="${dropDownValues}"/>
                        <form:errors path="guilty" cssClass="error"/>
                    </td>
                    <td>
                        Determined
                        <form:select path="determined" items="${dropDownValues}"/>
                        <form:errors path="determined" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Scared
                        <form:select path="scared" items="${dropDownValues}"/>
                        <form:errors path="scared" cssClass="error"/>
                    </td>
                    <td>
                        Attentive
                        <form:select path="attentive" items="${dropDownValues}"/>
                        <form:errors path="attentive" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Hostile
                        <form:select path="hostile" items="${dropDownValues}"/>
                        <form:errors path="hostile" cssClass="error"/>
                    </td>
                    <td>
                        Jittery
                        <form:select path="jittery" items="${dropDownValues}"/>
                        <form:errors path="jittery" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Enthusiastic
                        <form:select path="enthusiastic" items="${dropDownValues}"/>
                        <form:errors path="enthusiastic" cssClass="error"/>
                    </td>
                    <td>
                        Active
                        <form:select path="active" items="${dropDownValues}"/>
                        <form:errors path="active" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Proud
                        <form:select path="proud" items="${dropDownValues}"/>
                        <form:errors path="proud" cssClass="error"/>
                    </td>
                    <td>
                        Afraid
                        <form:select path="afraid" items="${dropDownValues}"/>
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
</div>

</body>
</html>