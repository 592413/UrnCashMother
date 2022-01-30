"use strict";
var app = angular.module('app',[]);

app.directive("alertMessage",function($compile) {
	return {
		restrict : "E",
		scope : {
			alert : "="
		},
		link : function(scope, element) {
			scope.$watch('alert', function() {
				updateAlert();
			});
			scope.close = function() {
				scope.alert = null;
			}
			function updateAlert() {
				var html = "";
				if (scope.alert) {
					var icon = null;
					switch (scope.alert.type) {
					case 'success': {
						icon = 'fa fa-check-circle';
					}
						break;
					case 'warning': {
						icon = 'fa fa-exclamation';
					}
						break;
					case 'info': {
						icon = 'fa fa-info';
					}
						break;
					case 'danger': {
						icon = 'fa fa-times';
					}
						break;
					}
					html = "<div class='pre-div' style='background-color: rgba(255, 255, 255, 0.6);'><div class='col-md-12 floatMessage'><div class='alert alert-"+ scope.alert.type	+ "' role='alert'>";
					if (scope.alert.closable) {
						html += "<button type='button' class='close' data-dismiss='alert' ng-click='close()' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
					}
					if (icon) {
						html +="<span><b>Message</b><hr></span>";
						html += "<span style='padding-right: 5px;' i class='"+icon+"' aria-hidden='true'></span>";
					}
					html += scope.alert.text;
					html += "</div></div></div>";
				}
				var newElement = angular.element(html);
				var compiledElement = $compile(newElement)(scope);
				element.html(compiledElement);
				if (scope.alert && scope.alert.delay > 0) {
					setTimeout(function() {
						scope.alert = null;
						scope.$apply();
						}, scope.alert.delay * 1000);
				}
			}
		}
	}
});

app.controller('appController',function($scope, $http, $window){
	$scope.showAlert = function(alertType, alertText) {
		$scope.alertMessage = {
			type : alertType,
			text : alertText,
			closable : true,
			delay : 3
		};
	};
	$scope.user = {};
	$scope.userLogin = function(){
		$scope.loader=true;
		if($scope.user.username === null || $scope.user.username === undefined || $scope.user.username === ""){
			$scope.loader=false;
			$scope.showAlert("danger","Enter Username");
			return false;
		}
		
		if($scope.user.password === null || $scope.user.password === undefined || $scope.user.password === ""){
			$scope.loader=false;
			$scope.showAlert("danger","Enter Password");
			return false;
		}
		$http.post('performLogin',$scope.user).success(function(data){
			$scope.loader=false;
			if(data.status == '1'){
				$scope.showAlert("success",data.message);
				$window.location.href = data.nextPage;
			}else{
				$scope.showAlert("danger",data.message);
			}
		});
		
	}
	
	 $scope.resellerdt={};
		var urii=document.getElementById("uri").value;
		$scope.mp={"url":urii};
		console.log($scope.mp);
		$http.post('getReseller',$scope.mp).success(function(data) {
			console.log(data);
			if(data.status === "1"){
				$scope.loader=false;
			$scope.resellerdt=data.reselletList;
			$scope.indexListdt=data.indexList;
			/*//console.log($scope.resellerdt);
			var base64String1 = $scope.Uint8ToBase64($scope.indexListdt[0].about_image);
			document.getElementById("logoimg").src = "data:image/png;base64," + base64String1;*/
			var base64String = $scope.Uint8ToBase64($scope.resellerdt[0].image);
			document.getElementById("logo").src = "data:image/png;base64," + base64String;
			//$window.location.href = data.nextPage;
			}
		});
	
	$scope.filterValue = function($event){
		if(isNaN(String.fromCharCode($event.keyCode))){
			$event.preventDefault();
		}
	};
	$scope.back = function(){
		$window.location.href="http://www.pay2on.com/";
	};
	
	 
		$scope.Uint8ToBase64 = function(ab){
			var dView = new Uint8Array(ab);   //Get a byte view    
		    var arr = Array.prototype.slice.call(dView); //Create a normal array   
		    var arr1 = arr.map(function(item){        
		      return String.fromCharCode(item);    //Convert
		    });

		    return window.btoa(arr1.join('')); 
		}
	$scope.forgotPassword = function(details){
		$scope.loader=true;
		$http.post('forgotPassword',details).success(function(data){
			$scope.loader=false;
			if(data.status === "1"){
				$scope.showAlert("success",data.message);
			} else {
				$scope.showAlert("danger",data.message);
			}
		}).error(function(data){
			$scope.loader=false;
			$scope.showAlert("danger","Technical Error! Please try again.");
		});
	}
	

	$scope.addEnquery = function(details){
		$scope.loader=true;
		console.log(details);
		var pattern=/^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
		
		if(details.UserName === undefined || details.UserName === null || details.UserName === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter your Name!");
			return false;
		}
		
		if(details.Address === undefined || details.Address === null || details.Address === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter your Address!");
			return false;
		}
		
		if(!pattern.test(details.mail_id)){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter your valid Email!");
			return false;
		}
		if(details.mail_id === undefined || details.mail_id === null || details.mail_id === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter your Email!");
			return false;
		}
		if(details.mobile === undefined || details.mobile === null || details.mobile === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter your Mobile No!");
			return false;
		}
		
		if(details.remark === undefined || details.remark === null || details.remark === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter remark!");
			return false;
		}
		
		$http.post('addEnquery', details).success(function(data) {
			$scope.loader=false;			
			if(data.status == "1"){
				$scope.showAlert("success", data.message);
			}else{
				$scope.showAlert("danger", data.message);
			}
		
		}).error(function(data){
			$scope.loader=false;
			$scope.showAlert("danger", "Technical Error. Please try after sometime");
		});
	};
	
	$http.get('getMsg').success(function(data){		
		if(data.message == undefined || data.message == ""){			
		}else{
			$scope.showAlert("danger",data.message);
		}	
	});
});