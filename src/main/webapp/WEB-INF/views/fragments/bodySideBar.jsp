<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>


<!-- sidebar -->
<div class="col-sm-3 col-md-3">
    <div class="panel-group" id="accordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                    	<span class="glyphicon glyphicon-home"></span> Home</a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse">
                <div class="panel-body">
                    <table class="table">
                        <tr>
                            <td>
                                <a href="<c:url value="/user-homepage"/>">My Status</a>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <a href="<c:url value="/user-homepage/depression-assessment"/>">Depression Assessment</a>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <a href="<c:url value="/user-homepage/assessment-result"/>">Assessment Result</a>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <a href="<c:url value="/user-homepage/other-post"/>">Other's post</a>
                          </td>
                      </tr>
                  </table>
              </div>
          </div>
      </div>
      <div class="panel panel-default">
          <div class="panel-heading">
              <h4 class="panel-title">
                  <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Card Game</a>
              </h4>
          </div>
          <div id="collapseTwo" class="panel-collapse collapse"> 
              <div class="panel-body">
                  <table class="table">
                  	  <tr>
                          <td>
                              <a href="<c:url value="/card-game/"/>">Card Game</a> 
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <a href="<c:url value="/card-game/myCards"/>">My Cards</a> 
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <a href="<c:url value="/card-game/customCards"/>">Custom Cards</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="<c:url value="/card-game/playHistory"/>">Play History</a>
                            </td>
                        </tr>
                        <tr>
                        	<td>
                            	<a href="<c:url value="/card-game/cardBrowser"/>">Card Browser</a> 
                          	</td>
                      	</tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">Express Your Mood</a>
                </h4>
            </div>
            <div id="collapseThree" class="panel-collapse collapse">
                <div class="panel-body">
                    <table class="table">
                   
                        <tr>
                            <td>
                                  <a href="<c:url value="/mood-tracking/data"/>">Data</a> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="<c:url value="/mood-tracking/charts"/>">Charts</a> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="<c:url value="/mood-tracking/reports"/>">Reports</a> 
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!-- 
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">Forum</a>
                </h4>
            </div>
            <div id="collapseFour" class="panel-collapse collapse">
                <div class="panel-body">
                    <table class="table">
                        <tr>
                            <td>
                                <a href="#">Creat New Forum</a>
                            </td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
         -->
    </div>
</div>
