<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>

<html>
<head>

    <title>Reports</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"/>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <script>
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
  
<h3><a href="<c:url value="/mood-tracking"/>">Express Your Mood</a></h3>

<h1>Reports</h1>

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
    <div>
        <table>
            <tr>Start Date
                <form:input path="startDate" type="text" id="startDate"/>
                <form:errors path="startDate" cssClass="error"/>
            </tr>

            <tr>End Date
                <form:input path="endDate" type="text" id="endDate"/>
                <form:errors path="endDate" cssClass="error"/>
            </tr>
        </table>

        <div>
            <input type="submit" id="save" name="save" value="Generate Report"/>
        </div>
    </div>
</form:form>

<div>
    <div>

        <table border="1">
            <tr>

                <th>Date</th>
                <th>Rate Your Mood</th>
                <th>How well you coped with your task today</th>
                <th>How many hours did you sleep today (Hours)</th>
                <th>How long did you exercise today (Hours)</th>


                <th> Interested</th>
                <th> Irritable</th>
                <th> Distressed</th>
                <th> Alert</th>
                <th> Excited</th>
                <th> Ashamed</th>
                <th> Upset</th>
                <th> Inpired</th>
                <th> Strong</th>
                <th> Nervous</th>
                <th> Guilty</th>
                <th> Determined</th>
                <th> Scared</th>
                <th> Attentive</th>
                <th> Hostile</th>
                <th> Jittery</th>
                <th> Enthusiastic</th>
                <th> Active</th>
                <th> Proud</th>
                <th> Afraid</th>

            </tr>
            <c:forEach var="_data" items="${dataItems}">
                <tr>
                    <td>${_data.date}</td>
                    <td>${_data.moodRating}</td>
                    <td>${_data.copedWithTask}</td>
                    <td>${_data.hoursOfSleeping}</td>
                    <td>${_data.exerciseHours}</td>
                    <td>${_data.interested}</td>
                    <td>${_data.irritable}</td>
                    <td>${_data.distressed}</td>
                    <td>${_data.alert}</td>
                    <td>${_data.excited}</td>
                    <td>${_data.ashamed}</td>
                    <td>${_data.upset}</td>
                    <td>${_data.inspired}</td>
                    <td>${_data.strong}</td>
                    <td>${_data.nervous}</td>
                    <td>${_data.guilty}</td>
                    <td>${_data.determined}</td>
                    <td>${_data.scared}</td>
                    <td>${_data.attentive}</td>
                    <td>${_data.hostile}</td>
                    <td>${_data.jittery}</td>
                    <td>${_data.enthusiastic}</td>
                    <td>${_data.active}</td>
                    <td>${_data.proud}</td>
                    <td>${_data.afraid}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>