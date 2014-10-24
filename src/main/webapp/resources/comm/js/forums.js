moodFlip.controller('ForumsCtrl', [ '$scope', '$http', function($scope, $http) {
	
	$scope.initTopicAndComments = function (topicId) {
		
		$http.get(appUrl+'/forums/topic?id='+topicId).success(function(data) {
			$scope.topic = data;
				$scope.topic.createdAt = new Date($scope.topic.createdAt).toLocaleString();
				if($scope.topic.editedAt)
					$scope.topic.editedAt = new Date($scope.topic.editedAt).toLocaleString();
		});
		
		$http.get(appUrl+'/forums/comment/list?topicId='+topicId).success(function(data) {
			$scope.comments = data;
			for (var i=0,  tot=$scope.comments.length; i < tot; i++) {
				$scope.comments[i].createdAt = new Date($scope.comments[i].createdAt).toLocaleString();
				if($scope.comments[i].editedAt)
					$scope.comments[i].editedAt = new Date($scope.comments[i].editedAt).toLocaleString();
			}
		});
		
		$http.get(appUrl+'/forums/card_suggest/list?topicId='+topicId).success(function(data) {
			$scope.cards = data;
		});
		
	};
	
	$scope.upVoteTopic = function () {
		$http.get(appUrl+'/forums/topic/up_vote/'+$scope.topic.id).success(function(data) {
			if(data) {
				$scope.topic.upVote = $scope.topic.upVote +1;
			}
		});
	};
	
	$scope.downVoteTopic = function () {
		$http.get(appUrl+'/forums/topic/down_vote/'+$scope.topic.id).success(function(data) {
			if(data) {
				$scope.topic.downVote = $scope.topic.downVote +1;
			}
		});
	};
	
	$scope.deleteComment = function (id) {
		$http.get(appUrl+'/forums/comment/delete/'+id).success(function(data) {
			if(data) {
				for (var i=0,  tot=$scope.comments.length; i < tot; i++) {
					if($scope.comments[i].id == id) {
						$scope.comments.splice(i, 1);
						break;
					}
				}
			}
		});
	};
	
	$scope.upVoteComment = function (id) {
		$http.get(appUrl+'/forums/comment/up_vote/'+id).success(function(data) {
			if(data) {
				for (var i=0,  tot=$scope.comments.length; i < tot; i++) {
					if($scope.comments[i].id == id) {
						$scope.comments[i].upVote = $scope.comments[i].upVote + 1;
						break;
					}
				}
			}
		});
	};
	
	$scope.downVoteComment = function (id) {
		$http.get(appUrl+'/forums/comment/down_vote/'+id).success(function(data) {
			if(data) {
				for (var i=0,  tot=$scope.comments.length; i < tot; i++) {
					if($scope.comments[i].id == id) {
						$scope.comments[i].downVote = $scope.comments[i].downVote + 1;
						break;
					}
				}
			}
		});
	};
	
} ]);