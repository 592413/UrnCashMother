"use strict";
var app = angular.module('app',['ui.bootstrap','ngSanitize','ngCookies','rzSlider'])

.directive("alertMessage",function($compile) {
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
					html = "<div class='loader'><div class='pre-div' style='background-color: rgba(255, 255, 255, 0.6);'><div class='col-md-12 floatMessage'><div class='alert alert-"+ scope.alert.type + "' role='alert'>";
					if (scope.alert.closable) {
						html += "<button type='button' class='close' data-dismiss='alert' ng-click='close()' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
					}
					if (icon) {
						html +="<span><b>Message</b><hr></span>";
						html += "<span style='padding-right: 5px;' i class='"+icon+"' aria-hidden='true'></span>";
					}
					html += scope.alert.text;
					html += "</div></div></div></div>";
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
}).filter('totalCommission', function() {
	return function(data, key) {		
		if (angular.isUndefined(data) || angular.isUndefined(key))
			return 0;
		var sum = 0;
		
		angular.forEach(data, function(v, k) {
				sum = sum + v[key];
		});
		return sum;
	}
})


//########################### By Romita Saha (Rating Directive for Star) #######################


.directive('starRating', function () {
    return {
        restrict: 'A',
        template: '<ul class="rating">' +
            '<li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">' +
            '\u2605' +
            '</li>' +
            '</ul>',
        scope: {
            ratingValue: '=',
            max: '=',
            onRatingSelected: '&'
        },
        link: function (scope, elem, attrs) {

            var updateStars = function () {
                scope.stars = [];
                for (var i = 0; i < scope.max; i++) {
                    scope.stars.push({
                        filled: i < scope.ratingValue
                    });
                }
            };

            scope.toggle = function (index) {
                scope.ratingValue = index + 1;
                scope.onRatingSelected({
                    rating: index + 1
                });
            };

            scope.$watch('ratingValue', function (oldVal, newVal) {
                if (newVal) {
                    updateStars();
                }
            });
        }
    }
})

.controller('appController', ["$scope", "$http", "$window", "$parse", "$sce", "$cookieStore", "$filter","$location",function($scope, $http, $window,$parse,$sce, $cookieStore, $filter,$location) {
	
	$scope.showAlert = function(alertType, alertText) {		
		$scope.alertMessage = {
			type : alertType,
			text : alertText,
			closable : true,
			delay : 5
		};
	};
	
	
	
	$scope.getCityAutoFill = function(str, id) {
		var fieldId = "#"+id;
		
		if (str.length>1) {
		
			//var CityAutoFill = {"term":str,"airports":$scope.mydata.airports};
			var CityAutoFill = {"term":str};
			
			$http.post('DestinationCities',CityAutoFill).success(function(data) {	
				console.log(data);
				$(fieldId).autocomplete({
				      source:data
				    });
			});

		}
	}
	

	
	$http.post('getFlightDashBoardDetails').success(function(data) {		
		console.log(data);
		if (data.status == '1') {
			$scope.userDetails = data.userDetails;	
			$scope.News = data.news;			
			$scope.reseller = data.reseller;
			 /*$scope.myairportdata =  data.airport;
			 console.log($scope.myairportdata);*/
		} else {
			$scope.showAlert("danger", "Invalid Login details!");
		}		
	});	
	
	
///	$scope.hotelsearch.rooms = 1;	
	$scope.searchHotal = function(details) {
		console.log(details);
		$scope.loader = true;
			$http.post('searchHotal',details).success(function(data) {	
				console.log(data);
				$scope.loader = false;
				if(data.status=='1'){
					$scope.userTrackId=data.output.TrackId;	
					$scope.hotelsearch=details;
				$scope.hotelresult=data.output;	
				
				//-------------------------by Romita
				$scope.hotelList = $scope.hotelresult.HotelResults;
				$scope.hotelListRevamp = $scope.hotelresult.HotelResults;
				
				if ($scope.hotelListRevamp.length > 0) {
					$scope.getPriceRangeSlider();
				}
				//------------
				
				$scope.guests=data.RoomGuests;	
				showDiv('div1');
				}else{
					$scope.showAlert("danger", data.output.ErrorMessage);
				}
			
			});

	}
	
	
	
	
	
	
//################ Romita Saha ########################
	
	$scope.getMinMaxPrice = function() {
		$scope.priceList = [];
		for (var i = 0; i < $scope.hotelListRevamp.length; i++) {
			if ($scope.hotelListRevamp[i].HotelPrice.totalprice > 0) {
				$scope.priceList.push($scope.hotelListRevamp[i].HotelPrice.totalprice);
			}
		}
		
		$scope.hotelMinFairVal = Math.min.apply(Math,$scope.priceList);
		$scope.hotelMaxFairVal = Math.max.apply(Math,$scope.priceList);
		
		console.log("###############################################");
		console.log($scope.priceList);
		console.log("################ MinFair ######################");
		console.log($scope.hotelMinFairVal);
		console.log("################ MaxFair ######################");
		console.log($scope.hotelMaxFairVal);
	}
	
	
	$scope.getPriceRangeSlider = function() {
		
		$scope.getMinMaxPrice();
		
		if ($scope.hotelMinFairVal > 0 && $scope.hotelMaxFairVal > 0) {
			
			if ($scope.hotelListRevamp.length>0) {
				
				$scope.hotel_slider_translate = {
			            minValue: $scope.hotelMinFairVal,
			            maxValue: $scope.hotelMaxFairVal,
			            options: {
			                ceil: $scope.hotelMaxFairVal,
			                floor: $scope.hotelMinFairVal,
			                translate: function (value) {
			                    return 'Rs. ' + Math.round(value);
			                },
			                onEnd: function () {
			                	 $scope.hotelPiceRangeFilter($scope.hotel_slider_translate.minValue, $scope.hotel_slider_translate.maxValue);
				            }
			            }
			        };
			}
			
		}
		
	}	
	
	$scope.hotelPiceRangeFilter = function (minPrice, maxPrice){
		 console.log("Min ::: " +minPrice + "Max :: "+ maxPrice);
		 $scope.hotelListTemp = [];
		 
		 $scope.hotelList = [];
		 $scope.hotelList = $scope.hotelListRevamp;
			 for (var i = 0; i < $scope.hotelListRevamp.length; i++) {
				if ($scope.hotelListRevamp[i].HotelPrice.totalprice >= minPrice && $scope.hotelListRevamp[i].HotelPrice.totalprice <= maxPrice) {
					$scope.hotelListTemp.push($scope.hotelListRevamp[i]);
					//console.log("DDEEDDEEDDEE :::::: ");
					//console.log($scope.flights[i]);
				}
			}
			 console.log("###### hotelListTemp :::::: ");
			 console.log($scope.hotelListTemp);
			 $scope.hotelList = $scope.hotelListTemp;
	 }
	
	//--------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	$scope.searchHotalDetails = function(details) {
		console.log(details);
		$scope.inputjson={"userTrackId":$scope.userTrackId,"ResultIndex":details.ResultIndex,"Searchid":details.Searchid,"HotelId":details.HotelId,"Providerid":details.Providerid};
		$scope.loader = true;
			$http.post('searchHotalDetails',$scope.inputjson).success(function(data) {	
				console.log(data);
				$scope.loader = false;
				if(data.status=='1'){
					$scope.hotelimage=details.HotelImage;
					$scope.userTrackId=data.output.TrackId;
					$scope.searchhotelreq=details;	
				$scope.hoteldetailsresult=data.output;	
				$scope.hotelImageList = data.output.HotelImages;
				showDiv('div2');
				}else{
					$scope.showAlert("danger", "No details Found!");
				}
			
			});

	}
	
	
	
	//############ Hotel Image Slider by Romita Saha ####################
	$scope.myInterval = 3000;
	/*$scope.HotelImagesSlider=function(){
		 console.log( 'Images-----------------' + $scope.hotelImageList.length);
				 if($scope.hotelImageList.length>0){
						setTimeout(function(){
							alert('Owl Images Are Coming')
							$('.owl-carousel').owlCarousel({
						        margin: 10,
						        items:1,
						        autoplayth: true,
						        nav: true,
						        navText:["<div class='nav-btn prev-slide'></div>","<div class='nav-btn next-slide'></div>"]
						    });
							
							}, 10000);
					}
	 }
	*/
	//#################################################################
	
	$scope.getroomblock = function(details) {
		$scope.loader=true;
		$scope.inputroomblock={"userTrackId":$scope.userTrackId,"roomno":parseInt($scope.hotelresult.RoomsNo),"nightno":$scope.hotelresult.nightno,"checkin":$scope.hotelresult.CheckIn,"searchhotelreq":$scope.searchhotelreq,"hoteldetailsresult":$scope.hoteldetailsresult,"request":details};
		console.log($scope.inputroomblock);
			$http.post('getroomblock',$scope.inputroomblock).success(function(data) {	
				console.log(data);
				$scope.loader = false;
				if(data.status=='1'){
					$scope.userTrackId=data.output.TrackId;	
				$scope.hotelblockresult=data.output;	
				showDiv('div3');
				}else if(data.status=='1'){
					$scope.showAlert("danger", "Room Avalibility Not Confirmed.Please select another Room!");
				}else{
					$scope.showAlert("danger", "No details Found!");
				}
			
			});

	}
	
	
	$scope.getroombook = function(details) {
		$scope.loader=true;
		var pattern=/^[a-z0-9]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
		if(!pattern.test(details.email)){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter your valid Email!");
			return false;
		}
		if(details.email === undefined || details.email === null || details.email === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter your Email!");
			return false;
		}
		if(details.mobile === undefined || details.mobile === null || details.mobile === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter your valid Mobile Number!");
			return false;
		}
		if(details.adulttitel11 === undefined || details.adulttitel11 === null || details.adulttitel11 === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Select your title!");
			return false;
		}
		if(details.adultfname11 === undefined || details.adultfname11 === null || details.adultfname11 === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter First Name!");
			return false;
		}
		if(details.adultlname11 === undefined || details.adultlname11 === null || details.adultlname11 === "" ){
			$scope.loader=false;	
			$scope.showAlert("info", "Enter Last Name");
			return false;
		}
		$scope.inputroombook={"userTrackId":$scope.userTrackId,"nightno":$scope.hotelresult.nightno,"checkin":$scope.hotelresult.CheckIn,"searchhotelreq":$scope.searchhotelreq,"hotelblockresult":$scope.hotelblockresult,"request":details,"guest":$scope.guests};
		console.log($scope.inputroombook);
			$http.post('getroombook',$scope.inputroombook).success(function(data) {	
				console.log(data);
				$scope.loader = false;
				if(data.status=='1'){
					$scope.userTrackId=data.output.TrackId;	
				$scope.hotelblockresult=data.output;	
				showDiv('div3');
				}else{
					$scope.showAlert("danger", "No details Found!");
				}
			
			});

	}
	
	
	
	/*var days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
	var months = ['January','February','March','April','May','June','July','August','September','October','November','December'];*/
 
	var days = ['Sun','Mon','Tue','Wed','Thur','Fri','Sat'];
	var months = ['Jan','Feb','Mar','Apr','May','June','July','Aug','Sep','Oct','Nov','Dec'];
	
  
      
   
      
	 	 
	 	$scope.printInvoice = function(divId) {	    	
	    	var myDiv = "#"+divId;
	    	
    	     var printContents = $(myDiv).html();
    	     //console.log(printContents);
    	     
    	     var newWin=window.open('','Print-Window');
    	     newWin.document.open();
    	     newWin.document.write('<html><body onload="window.print()">'+
    	    		 '<link rel="stylesheet" type="text/css" href="assets/skyflight/plugins/print.css">'+
    	    		 '<link href="assets/skyflight/plugins/responsive.bootstrap.min.css" rel="stylesheet" type="text/css" />' +
    	    			'<link href="assets/skyflight/plugins/bootstrap.min.css" rel="stylesheet" type="text/css">'+
    	    			'<link rel="stylesheet" href="assets/skyflight/plugins/bootstrap-datepicker.css">'+printContents+'</body></html>');
    	     newWin.document.close();
    	     setTimeout(function(){newWin.close();},10);
		}
	 	 

	 	
$scope.showFlightDetailTabs = function(id) {
	var div = "#tabDetail" + id;
	$('div[id^=tabDetail]').hide();
	$(div).toggle();
}

$scope.roundFlightDetailTabs = function(id) {
	var div = "#roundtabDetail" + id;
	$('div[id^=roundtabDetail]').hide();
	$(div).toggle();
}
	

$scope.roundtFlightDetailTabs = function(id) {
	var div = "#roundttabDetail" + id;
	$('div[id^=roundttabDetail]').hide();
	$(div).toggle();
}


	
}]);




function showDiv(divId) {
	var div = "#" + divId;
	$('div[id^=div]').hide();
	$(div).slideToggle(600).show();
}

function sshowDiv(divId) {
	var div = "#" + divId;
	$('div[id^=sdiv]').hide();
	$(div).slideToggle(600).show();
}

