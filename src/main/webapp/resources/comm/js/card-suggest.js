moodFlip.controller('CardSuggestCtrl', ['$scope', '$http', function ($scope, $http) {
	
	$scope.selectedSymptom = "All Symptoms"
	
	$scope.init = function (topicId) {
		$scope.topicId = topicId;
		$scope.listCards();
		$scope.listSymptoms();
		console.log($scope.topicId);
	};
	
	$scope.listCards = function () {
		$http.get(appUrl+'/forums/card_suggest/listCards').success(function(data) {
			$scope.columns = splitArray(data);
		});
	};
	
	$scope.listCardsBySymptom = function () {
		$http.get(appUrl+'/forums/card_suggest/listCards?symptom=' + $scope.selectedSymptom).success(function(data) {
			$scope.columns = splitArray(data);
		});
	};
	
	function splitArray (array) {
		var result = [];
		var temp = [];
		for(var i = 0; i < array.length; i++) {
			if(i % 4 == 0 && i != 0) {
				result.push(temp);
				temp = [];
			}
			temp.push(array[i]);
		}
		if(temp.lenth != 0) {
			result.push(temp);
		}
		return result;
	};
	
	$scope.listSymptoms = function () {
		$http.get(appUrl+'/forums/card_suggest/listSymptoms').success(function(data) {
			$scope.symptoms = data;
		});
	};
	
	$scope.setSymptomAndListCards = function (symptom) {
		$scope.selectedSymptom = symptom;
		if (symptom == "All Symptoms") {
			$scope.listCards();
		} else {
			$scope.listCardsBySymptom();
		}
		$scope.selectedIds = [];
	}
	
	$scope.selectedIds = [];
	
	$scope.setSelected = function (cardId) {
		if($scope.selectedIds.indexOf(cardId) == -1) {
			$scope.selectedIds.push(cardId);
		} else {
			$scope.selectedIds.splice($scope.selectedIds.indexOf(cardId), 1);
		}
	};
	
	$scope.postSuggest = function () {
		$http.post(appUrl+'/forums/topic/' + $scope.topicId + '/suggestCard', $scope.selectedIds).success(function(data) {
			document.location.href = (appUrl + data);
		});
	};
	
}]);