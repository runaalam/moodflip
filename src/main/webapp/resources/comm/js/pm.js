moodFlip.controller('PrivateMessageCtrl', ['$scope', '$http', '$timeout', '$q', function ($scope, $http, $timeout, $q) {
	
	var canceler = $q.defer();
	
	var ongoing = false;
	
	$scope.setReceiverId = function (receiverId) {
		if(receiverId != null) {
			$scope.receiverId = receiverId;
			if(ongoing) {
				$scope.cancel();
			}
			$scope.listMessage();
			$scope.userSelected = true;
		}
	};
	
	$scope.listMessage = function () {
		$http.get('pm/'+$scope.receiverId).success(function(data) {
			$scope.messages = data;
			for (var i=0,  tot=$scope.messages.length; i < tot; i++) {
				$scope.messages[i].createdAt = new Date($scope.messages[i].createdAt).toLocaleString();
			}
		}).then(function () {
			$scope.updateMessage();
		});
	};
	
	$scope.updateMessage = function () {
		ongoing = true;
		if($scope.messages.length > 0) {
			$http.get('pm/'+$scope.receiverId+"/update?lastId="+$scope.messages[$scope.messages.length-1].id, { timeout: canceler.promise }).success(function(data) {
				if(data.length > 0){
					for (var i=0,  tot=data.length; i < tot; i++) {
						data[i].createdAt = new Date(data.createdAt).toLocaleString();
					}
					$scope.messages = $scope.messages.concat(data);
				}
			}).then(function () {
				$scope.updateMessage();
			});
		} else {
			$http.get('pm/'+$scope.receiverId+"/update?lastId=0", { timeout: canceler.promise }).success(function(data) {
				if(data.length > 0){
					for (var i=0,  tot=data.length; i < tot; i++) {
						data[i].createdAt = new Date(data.createdAt).toLocaleString();
					}
					$scope.messages = $scope.messages.concat(data);
				}
			}).then(function () {
				$scope.updateMessage();
			});
		}
	};
	
	$scope.cancel = function(){
	    canceler.resolve();
	    ongoing = false;
	    canceler = $q.defer();
	};
	
	$scope.send = function () {
		$http.post('pm/'+$scope.receiverId, $scope.message).success(function(data) {
			$scope.message = "";
			$scope.messages.push(data);
		});
	};
	
}]);
