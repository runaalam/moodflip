moodFlip.controller('CardSuggestCtrl', ['$scope', '$http', function ($scope, $http) {
	$scope.listCards = function () {
		$http.get('forums/card_suggest/listCards').success(function(data) {
			$scope.cards = data;
		});
	};
}]);