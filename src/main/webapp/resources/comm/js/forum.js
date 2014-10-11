moodFlip.controller('ForumCtrl', [ '$scope', '$http', function($scope, $http) {
	
	$scope.initTopicAndComments = function (topicId) {
		
		$http.get(appUrl+'/forum/topic?id='+topicId).success(function(data) {
			$scope.topic = data;
				$scope.topic.createdAt = new Date($scope.topic.createdAt).toLocaleString();
				if($scope.topic.editedAt)
					$scope.topic.editedAt = new Date($scope.topic.editedAt).toLocaleString();
		});
		
		$http.get(appUrl+'/forum/comment/list?topicId='+topicId).success(function(data) {
			$scope.comments = data;
			for (var i=0,  tot=$scope.comments.length; i < tot; i++) {
				$scope.comments[i].createdAt = new Date($scope.comments[i].createdAt).toLocaleString();
				if($scope.comments[i].editedAt)
					$scope.comments[i].editedAt = new Date($scope.comments[i].editedAt).toLocaleString();
			}
		});
		
	};
	
	$scope.upVoteTopic = function () {
		$http.get(appUrl+'/forum/topic/up_vote/'+$scope.topic.id).success(function(data) {
			if(data) {
				$scope.topic.upVote = $scope.topic.upVote +1;
			}
		});
	};
	
	$scope.downVoteTopic = function () {
		$http.get(appUrl+'/forum/topic/down_vote/'+$scope.topic.id).success(function(data) {
			if(data) {
				$scope.topic.downVote = $scope.topic.downVote +1;
			}
		});
	};
	
	$scope.deleteComment = function (id) {
		$http.get(appUrl+'/forum/comment/delete/'+id).success(function(data) {
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
		$http.get(appUrl+'/forum/comment/up_vote/'+id).success(function(data) {
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
		$http.get(appUrl+'/forum/comment/down_vote/'+id).success(function(data) {
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