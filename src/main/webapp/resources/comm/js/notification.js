moodFlip.controller('NotificationCtrl', ['$scope', '$http', function ($scope, $http) {
	
	$scope.notifications = [];
	
	$scope.listNotification = function () {
		$http.get(appUrl+'/notification/list').success(function(data) {
			$scope.notifications = data;
			for (var i=0,  tot=$scope.notifications.length; i < tot; i++) {
				$scope.notifications[i].createdAt = new Date($scope.notifications[i].createdAt).toLocaleString();
			}
			if($scope.notifications.length > 0) {
				$scope.latestId = $scope.notifications[0].id;
			} else {
				$scope.latestId = 0;
			}
		}).then(function () {
			$scope.updateNotification();
		});
	};
	
	$scope.updateNotification = function () {
		$http.get(appUrl+'/notification/update?id='+$scope.latestId).success(function(data) {
			if(data.length > 0) {
				for (var i=0,  tot=data.length; i < tot; i++) {
					data[i].createdAt = new Date(data[i].createdAt).toLocaleString();
				}
				$scope.notifications = data.concat($scope.notifications);
				$scope.latestId = $scope.notifications[0].id;
			}
		}).then(function () {
			$scope.updateNotification();
		});
	};
	
	$scope.unreadNotifications = [];
	$scope.showUnreadNotifications = [];
	
	$scope.listUnreadNotification = function () {
		$http.get(appUrl+'/notification/listUnread').success(function(data) {
			$scope.unreadNotifications = data;
			for (var i=0,  tot=$scope.unreadNotifications.length; i < tot; i++) {
				$scope.unreadNotifications[i].createdAt = new Date($scope.unreadNotifications[i].createdAt).toLocaleString();
			}
			if($scope.unreadNotifications.length > 0) {
				$scope.latestUnreadId = $scope.unreadNotifications[0].id;
			} else {
				$scope.latestUnreadId = 0;
			}
			$scope.showUnreadNotifications = $scope.unreadNotifications;
		}).then(function () {
			$scope.updateUnreadNotification();
		});
	};
	
	$scope.updateUnreadNotification = function () {
		$http.get(appUrl+'/notification/updateUnread?id='+$scope.latestUnreadId).success(function(data) {
			if(data.length > 0) {
				for (var i=0,  tot=data.length; i < tot; i++) {
					data[i].createdAt = new Date(data[i].createdAt).toLocaleString();
				}
				$scope.unreadNotifications = data.concat($scope.unreadNotifications);
				if($scope.unreadNotifications.length > 0) {
					$scope.latestUnreadId = $scope.unreadNotifications[0].id;
				} else {
					$scope.latestUnreadId = 0;
				}
				$scope.showUnreadNotifications = $scope.unreadNotifications;
			}
		}).then(function () {
			$scope.updateUnreadNotification();
		});
	};
	
	$scope.setMessagesRead = function () {
		if(!$("#notifications").hasClass("open") && $scope.unreadNotifications.length > 0) {
			var ids = [];
			for (var i=0,  tot=$scope.unreadNotifications.length; i < tot; i++) {
				ids.push($scope.unreadNotifications[i].id);
			}
			$http.post(appUrl+'/notification/read', ids).success(function(data) {
				$scope.unreadNotifications = [];
			});
		}
	};
	
	$scope.removeMessage = function (id) {
		$http.get(appUrl+'/notification/remove/'+id).success(function(data) {
			for (var i=0,  tot=$scope.notifications.length; i < tot; i++) {
				if($scope.notifications[i].id == id) {
					$scope.notifications.splice(i, 1);
					break;
				}
			}
		});
	};
	
}]);