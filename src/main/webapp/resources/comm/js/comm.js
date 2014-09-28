var moodFlip = angular.module('moodFlip', []);

moodFlip.controller('ForumCtrl', ['$scope', '$http', function ($scope, $http) {
	$scope.listForum = function () {
		$http.get('forum/list').success(function(data) {
			$scope.forums = data;
		});
	};
}]);

moodFlip.controller('PrivateMessageCtrl', ['$scope', '$http', function ($scope, $http) {
	$scope.setReceiverId = function (receiverId) {
		$scope.receiverId = receiverId;
		$scope.listMessage();
	};
	
	$scope.listMessage = function () {
		$http.get('pm/'+$scope.receiverId).success(function(data) {
			$scope.messages = data;
		});
	};
	
	$scope.send = function () {
		$http.post('pm/'+$scope.receiverId, $scope.message).success(function(data) {
			$scope.message = "";
			$scope.messages.push(data);
		});
	};
	
}]);