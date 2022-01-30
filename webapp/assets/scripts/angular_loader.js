"use strict";
var app = angular.module('app',[]);
app.controller('appController',function($scope, $http, $window){
	
	 $scope.resellerdt={};
	var urii=document.getElementById("uri").value;
	$scope.mp={"url":urii};
	console.log($scope.mp);
	$http.post('getReseller',$scope.mp).success(function(data) {
		console.log(data);
		if(data.status === "1"){
		$scope.resellerdt=data.reselletList;
		$window.location.href = data.nextPage;
		}
	});
	
});
