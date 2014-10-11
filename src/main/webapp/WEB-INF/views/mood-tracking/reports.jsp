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
</head>
<body>
<h1>Report</h1>

<div>
    <div>
       
        <table border="1">
            <tr>
            	            	
            	<th>Date</th>
                <th>Rate Your Mood</th>
                <th>How well you coped with your task today</th>
                <th>How many hours did you sleep today  (Hours)</th>
                <th>How long did you exercise today     (Hours)</th>
                
                
           
                
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