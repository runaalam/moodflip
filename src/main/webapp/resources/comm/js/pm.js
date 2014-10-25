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
		}
	};
	
	$scope.listMessage = function () {
		$scope.requestTime = (new Date()).getTime();
		$http.get(appUrl+'/pm/'+$scope.receiverId+'/list').success(function(data) {
			$scope.messages = data;
			for (var i=0,  tot=$scope.messages.length; i < tot; i++) {
				$scope.messages[i].createdAt = new Date($scope.messages[i].createdAt).toLocaleString();
			}
		}).then(function () {
			setTimeout(scrollChatAreaToBottom, 1500);
			$scope.updateMessage();
		});
	};
	
	$scope.updateMessage = function () {
		ongoing = true;
		var lastRequestTime = (new Date()).getTime();
		$http.get(appUrl+'/pm/'+$scope.receiverId+"/update?datetime="+$scope.requestTime, { timeout: canceler.promise }).success(function(data) {
			if(data.length > 0){
				for (var i=0,  tot=data.length; i < tot; i++) {
					data[i].createdAt = new Date(data[i].createdAt).toLocaleString();
				}
				$scope.messages = $scope.messages.concat(data);
				scrollChatAreaToBottom();
			}
		}).then(function () {
			$scope.requestTime = lastRequestTime;
			$scope.updateMessage();
		});
	};
	
	function scrollChatAreaToBottom () {
		$(".chat-texts").animate({ scrollTop: $(".chat-texts")[0].scrollHeight }, "slow");
	};
	
	$scope.cancel = function(){
	    canceler.resolve();
	    ongoing = false;
	    canceler = $q.defer();
	};
	
	$scope.send = function () {
		$http.post(appUrl+'/pm/'+$scope.receiverId, $scope.message).success(function(data) {
			$scope.message = "";
			data.createdAt = new Date(data.createdAt).toLocaleString();
			$scope.messages.push(data);
		}).then(function () {
			scrollChatAreaToBottom();
		});
	};
	
}]);
