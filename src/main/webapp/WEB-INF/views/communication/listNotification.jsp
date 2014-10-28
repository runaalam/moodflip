<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html ng-app="moodFlip">
<head>
<title><fmt:message key="title" /></title>
<%@ include file="/WEB-INF/views/bootstrap/include-css.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/comm/css/notification.css" />">
<%@ include file="/WEB-INF/views/bootstrap/include-js.jsp"%>
</head>
<body>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>
	
	<div class="container">
	
	<div class="row">
		<div class="col-md-12">
			<h3>
				Notifications
			</h3>
		</div>
	</div>

	<div ng-controller="NotificationCtrl" ng-init="listNotification()">
		<div class="row">
			<div class="col-md-12">
					<div class="panel panel-default" ng-show="notifications.length">
						<table class="table table-hover">
							<tbody>
								<tr ng-repeat="notification in notifications">
									<td><a href="<c:url value="/{{notification.url}}"/>"><strong>{{notification.message}}</strong></a></td>
									<td class="text-right nowrap">
										{{notification.createdAt}}
										<button class="btn btn-sm btn-danger" ng-click="removeMessage(notification.id)">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div ng-hide="notifications.length">No Notifications</div>
			</div>
		</div>
	</div>
	
	</div>

	<%@ include file="/WEB-INF/views/fragments/footer.jsp"%>

</body>
</html>