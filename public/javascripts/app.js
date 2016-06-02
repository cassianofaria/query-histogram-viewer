var app = angular.module("app", [ "chart.js" ]);

app.controller("IndexCtrl", ['$scope', '$http', function($scope, $http) {

	$scope.updateHistogram = function() {
		$http({
			  method: 'GET',
			  url: '/histogram'
			}).then(function successCallback(response) {
				$scope.labels = response.data['bins'];
				$scope.series = [ 'Quantities' ];

				$scope.data = [ response.data['quantities'] ];
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  });
	};
	
	$scope.resetHistogram = function() {
		$http({
			  method: 'DELETE',
			  url: '/histogram'
			}).then(function successCallback(response) {
				$scope.updateHistogram();
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  });
	}
	
	$scope.updateHistogram();
	
	

}]);