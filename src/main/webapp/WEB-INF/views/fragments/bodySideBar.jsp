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
            <div id="collapseOne" class="panel-collapse collapse in">
                <div class="panel-body">
                    <table class="table">
                        <tr>
                            <td>
                                <span class="glyphicon glyphicon-pencil text-primary"></span>
                                <a href="<c:url value="/user-homepage"/>">My Status</a>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <span class="glyphicon glyphicon-flash text-success"></span>
                              <a href="<c:url value="/user-homepage/depression-assessment"/>">Depression Assessment</a>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <span class="glyphicon glyphicon-file text-info"></span>
                              <a href="<c:url value="/user-homepage/assessment-result"/>">Assessment Result</a>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <span class="glyphicon glyphicon-comment text-success"></span>
                              <a href="<c:url value="/user-homepage/other-post"/>">Other's Status</a>
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
                              <a href="#">My Cards</a> 
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <a href="#">Custom Cards</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#">Game Stats</a>
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
                                <a href="#">Data</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#">Chart</a> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#">Report</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
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
    </div>
</div>
