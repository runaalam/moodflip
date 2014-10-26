moodFlip.controller('NotificationCtrl', ['$scope', '$http', function ($scope, $http) {
	$scope.listNotification = function () {
		$http.get('notification/list').success(function(data) {
			$scope.notifications = data;
			for (var i=0,  tot=$scope.notifications.length; i < tot; i++) {
				$scope.notifications[i].createdAt = new Date($scope.notifications[i].createdAt).toLocaleString();
			}
		});
	};
}]);