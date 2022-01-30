"use strict";
var app = angular.module('app',['ngSanitize','ngCookies'])

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
	
	// #### For Live Url
	var ctx = window.location.origin;
	
	// ### For Local Url
	var ctxPath = window.location.pathname;
	var pathArr = ctxPath.split("/");
	/*console.log(ctx);
	console.log(ctxPath);
	console.log(pathArr);*/
	
	var locatCtx = ctx + "/" + pathArr[1];
	// ### For Local Url End
	
	//### Source & Destination Autofill 
	$scope.flight = {};
	$scope.getCityAutoFill = function(str, id) {
		//#### For Live
		//var url =  ctx +'/flightSourceDestination';
		//#### For Local
		var url =  locatCtx +'/flightSourceDestination';
		var fieldId = "#"+id;
		var data = {"term":str};
		if (str.length>1) {
			var req = {
					 method: 'POST',
					 url: url,
					 headers: {
					   'Content-Type': undefined
					 },
					 params: data
					};
		$http(req).then(function(responce){
		//	console.log(responce);
			$scope.responcecity = responce.data;
		//	console.log($scope.responcecity);
			$(fieldId).autocomplete({
			      source: $scope.responcecity
			    });
			});
		
		}
		
	}
	
	/*$scope.getCityAutoFill = function(str, id) {
		if (str.length>1) {
			var search=angular.uppercase(str);
		for(var i = 0; i<$scope.mydata.airports.length; i++){
			 $scope.myairports = $scope.mydata.airports[i];
			 var code=angular.uppercase($scope.myairports.code);
			 var city=angular.uppercase($scope.myairports.city);
			 var country=angular.uppercase($scope.myairports.country);
			 if(search.test(code)){
				 console.log($scope.myairports);
			 }
			 if(search.test(city)){
				 console.log($scope.myairports);
			 }
			 if(search.test(country)){
				 console.log($scope.myairports);
			 }
		}
		}
	}*/
	
	$scope.myOneWay = function() {
		
		document.getElementById("dp2").disabled = true;
		document.getElementById("dp4").disabled = true;
		document.getElementById("dp6").disabled = true;
		$scope.flight.return="";
	};
	
	$http.post('getDashBoardDetails').success(function(data) {		
		//console.log(data);
		if (data.status == '1') {
			$scope.userDetails = data.userDetails;	
			$scope.News = data.news;
			
			$scope.reseller = data.reseller;
			
			/*var base64String = $scope.Uint8ToBase64($scope.reseller.image);
			document.getElementById("logo").src = "data:image/png;base64," + base64String;
			document.getElementById("logo1").src = "data:image/png;base64," + base64String;*/
			
		} else {
			$scope.showAlert("danger", "Invalid Login details!");
		}		
	});
	
	
	
	/*var days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
	var months = ['January','February','March','April','May','June','July','August','September','October','November','December'];*/
 
	var days = ['Sun','Mon','Tue','Wed','Thur','Fri','Sat'];
	var months = ['Jan','Feb','Mar','Apr','May','June','July','Aug','Sep','Oct','Nov','Dec'];
	
    $scope.mydata = {};
    $scope.mydist ={};
      
      $.getJSON( "assets/airportJson/airports.json", function( data ) {
        $scope.mydata = data;
        console.log($scope.mydata );
      });
      
      $scope.myRoundTrip = function() {
  		
    	  document.getElementById("dp2").disabled = false; 
    	  document.getElementById("dp6").disabled = false; 
    	  document.getElementById("dp4").disabled = false; 
  		
  	};
     
      $scope.flight.type="O";
      if($scope.flight.type=='O'){
    	  document.getElementById("dp2").disabled = true;
    	  document.getElementById("dp6").disabled = true;
    	  document.getElementById("dp4").disabled = true;
      }else{
    	  document.getElementById("dp2").disabled = false; 
    	  document.getElementById("dp6").disabled = false; 
    	  document.getElementById("dp4").disabled = false; 
      }
      
      
      var flightsearchFlag = false;
      
	 $scope.searchFlight = function(detail){

 	  console.log(detail);
 	     var  flightssize = [];
 	     
 		$scope.loader = true;
 		 if(detail.class === undefined || detail.class === null ||detail.class === ""){
				$scope.loader=false;
				$scope.showAlert("info", "Enter Class Properly");
				return false;
			}
 		 if(detail.source === undefined || detail.source === null ||detail.source === ""){
	 				$scope.loader=false;
	 				$scope.showAlert("info", "Enter source Properly");
	 				return false;
	 			}
 		 if(detail.destination === undefined || detail.destination === null ||detail.destination === ""){
	 				$scope.loader=false;
	 				$scope.showAlert("info", "Enter destination Properly");
	 				return false;
	 			}
 		 
 		 if(detail.depart === undefined || detail.depart === null ||detail.depart === ""){
	 				$scope.loader=false;
	 				$scope.showAlert("info", "Enter Departure date Properly");
	 				return false;
	 			}
 		 console.log(detail.adult+detail.child);
 		 if(detail.adult+detail.child>12){
 			$scope.loader=false;
				$scope.showAlert("info", "More than 12 Passengers Not Allowed");
				return false;
 		 }
 		 if(detail.adult<detail.infant){
  			$scope.loader=false;
 				$scope.showAlert("info", "Infant can't travel more than Adult.");
 				return false;
  		 }
 		var source = detail.source.split(',');
	     var destination = detail.destination.split(',');
 		 if(source[2]=='India' && destination[2]=='India' ){
 		$http.post('searchFlight',detail).success(function(data){
 			/*console.log(data.UserTrackId);*/
 			console.log(data);
 			if(data.status != 0){
 				if(data.type=="O" ){
 				showDiv('div1');
 				
 				
	    			$scope.loader = false;
	    			$scope.min = data.minval;
	    			$scope.max = data.maxval;
	    			$scope.pricerangemax=$scope.max;
	    			$scope.pricerangemin=$scope.min;
	    			$scope.requestDetails = data.requestDetails;
	    			$scope.flight = data.requestDetails;
	    			$scope.flight.adult=parseInt(data.requestDetails.adult);
	    			$scope.flight.child=parseInt(data.requestDetails.child);
	    			$scope.flight.infant=parseInt(data.requestDetails.infant);
	    			$scope.adminmarkupdomoneway = data.adminmarkup;
	    			$scope.usermarkupdomoneway = data.usermarkup;
	    			$scope.sourceDetail = data.requestDetails.source.split(',');
	    			$scope.destinationDetail = data.requestDetails.destination.split(',');
	    			$scope.UserTrackId=data.UserTrackId;
	    			$scope.flights=data.report;
	    			
	    			var flags = [],
	                  output = [],
	    			nameoutput = [];
	    			
	    			for(var i=0;i<data.report.length;i++){
	    				//-----stops count---------------
	    			
	    				if (flags[$scope.flights[i].stops]) continue;
	                     flags[$scope.flights[i].stops] = true;
	                     output.push($scope.flights[i].stops);
	                     //------------------------------
	                     //-----flight name collect-------------
	                     if (flags[$scope.flights[i].AirlineName]) continue;
	                     flags[$scope.flights[i].AirlineName] = true;
	                     nameoutput.push($scope.flights[i].AirlineName);
	                     //---------------------------------
	    			}
	    			$scope.filterstop=output;
	    			$scope.filtername=nameoutput;
	    			$scope.fetchcalenderfare(detail);
	    			
	    			console.log("::::::::::");
	    			console.log(!flightsearchFlag);
	    			if (!flightsearchFlag) {
	    				$scope.fetchcalenderfare(detail);
		    			flightsearchFlag = true;
					}
	    			
	    			
		 			}else{
		 				showDiv('div2');
						
						console.log(data);
		 				$scope.loader=false;
		 				$scope.min = data.minval;
		    			$scope.max = data.maxval;
		    			$scope.pricerangemax=$scope.max;
		    			$scope.pricerangemin=$scope.min;
					$scope.requestDetails = data.requestDetails;
					$scope.flight = data.requestDetails;
					$scope.flight.adult=parseInt(data.requestDetails.adult);
	    			$scope.flight.child=parseInt(data.requestDetails.child);
	    			$scope.flight.infant=parseInt(data.requestDetails.infant);
					$scope.sourceDetail = data.requestDetails.source.split(',');
					$scope.destinationDetail = data.requestDetails.destination.split(',');
					$scope.UserTrackId=data.UserTrackId;
					$scope.flights=data.report;
					$scope.flightsre=data.report;
					//$scope.flightreturn=data.returnreport;
					
					$scope.flightsection1change($scope.flights[0],data.report,detail);
				
					var flags = [],
	                  output = [],
	    			nameoutput = [];
					for(var i=0;i<data.report.length;i++){
	    				if($scope.flights[i].tripindi==2){
	    					$scope.flightsection2change($scope.flights[i],data.report,detail);
	    				}
	    				
	    				//-----stops count---------------
		    			
	    				if (flags[$scope.flights[i].stops]) continue;
	                     flags[$scope.flights[i].stops] = true;
	                     output.push($scope.flights[i].stops);
	                     //------------------------------
	                     //-----flight name collect-------------
	                     if (flags[$scope.flights[i].AirlineName]) continue;
	                     flags[$scope.flights[i].AirlineName] = true;
	                     nameoutput.push($scope.flights[i].AirlineName);
	                     //---------------------------------
					}
					$scope.filterstop=output;
					console.log($scope.filterstop);
	    			$scope.filtername=nameoutput;
	    			console.log($scope.filtername);
	    			
	    			
	    			
	    			
			}
 				
 			}else{
 				$scope.loader=false;
 				//var message = "We are sorry.... Submit form again";
 				console.log(data.message);
 				$scope.showAlert("danger", data.message);
 			}
 			
 		}).error(function(data){
 			$scope.loader=false;
 			$scope.showAlert("danger", "Technical Error. Please try after sometime");
 		});
 		 }else{
 			 //  searchFSCFlightInternational searchFlightInternational
 			 $http.post('searchFlightInternational',detail).success(function(data){
 				console.log(data);
 				$scope.loader=false;
 				 if(data.status==1){
 				 if(data.type=="O" ){
 					showDiv('div1');
 					 $scope.requestDetails = data.requestDetails;
		    			$scope.sourceDetail = data.requestDetails.source.split(',');
		    			$scope.destinationDetail = data.requestDetails.destination.split(',');
		    			$scope.UserTrackId=data.UserTrackId;
		    			$scope.flights=data.report;
		    			$scope.flightreturn=data.returnreport;
		    			
 				 }else{
 					showDiv('div2');
				    console.log(data);
				    $scope.requestDetails = data.requestDetails;
	    			$scope.sourceDetail = data.requestDetails.source.split(',');
	    			$scope.destinationDetail = data.requestDetails.destination.split(',');
	    			$scope.UserTrackId=data.UserTrackId;
	    			$scope.flights=data.report;
	    			$scope.flightreturn=data.returnreport;
	    			
				    $scope.loader=false;
 				 }
 			 }else{
 				 sshowDiv('sdiv92');
		    			$scope.loader=false;
		    			$scope.showAlert("danger",data.FailureRemarks);
 			 }
 			 }).error(function(data){
	    			sshowDiv('sdiv92');
	    			$scope.loader=false;
	    			$scope.showAlert("danger", "Technical Error. Please try after sometime");
	    		});
 			 
 		 };
 	
	 };


	 $scope.fetchcalenderfare=function(detail){
		 $http.post('fetchcalenderfare',detail).success(function(data){
			 console.log(data);
			 if(data.status==1){
				 $scope.chcalenderfare=data.CalendarFareResult;
				 if($scope.chcalenderfare.length>0){
						setTimeout(function(){
							$('.owl-carousel').owlCarousel({
						        margin: 10,
						        autoplayth: true,
						        nav: true,
						        navText:["<div class='nav-btn prev-slide'></div>","<div class='nav-btn next-slide'></div>"]
						    });
							
							}, 3000);
					}
			 }
		 }).error(function(data){
 			
		});
	 };
	 
	 function owlres() {
	        var $carousel = $('.owl-carousel');
	        $carousel.trigger('refresh.owl.carousel');
	        
			console.log('owlres');
		}
	 
	 $scope.fetchcalenderflight=function(detail){
		 $scope.inputscope={
				 "depart":detail,
				 "type":$scope.flight.type,
				 "adult":$scope.flight.adult,
				 "child":$scope.flight.child,
				 "infant":$scope.flight.infant,
				 "class":$scope.flight.class,
				 "destination":$scope.flight.destination,
				 "source":$scope.flight.source};
		
		 console.log(detail);
		 $http.post('fetchcalenderflight',$scope.inputscope).success(function(data){
			 $scope.searchFlight($scope.inputscope);
		 }).error(function(data){
 			
		});
	 };
	 
	 $scope.pricerange=function(details){
		 
		var amount=details.Fare.TotalAmount;
		
		 var filtered = [];
	//	 angular.forEach(details, function(details) {
		        if( amount >= $scope.pricerangemin && amount <= $scope.pricerangemax ) {
		            filtered.push(details);
		         //   console.log(filtered);
		            return true;
		        }
		  //  });
		/* $scope.flights = filtered;*/
		    return false;
	 }
	 
	 $scope.locChkBox = {};
     $scope.locChkBox.loc = [];
	 $scope.stopFilter = function(item) {
		 var filterstop = [];
         if($scope.locChkBox.loc.isNull()) return true;
       //  var stps=$scope.locChkBox.loc.length-1;
         for (var i = 0; i < $scope.locChkBox.loc.length; i++) {
        	 if($scope.locChkBox.loc[i]!=='empty' || $scope.locChkBox.loc[i]!=='undefined'){
        	     if (item.stops === parseInt($scope.locChkBox.loc[i])){
        	               return true;
        		 } 
        	 }
      
         }
         return false;
       };
       
       
  	 $scope.nameChkBox = {};
     $scope.nameChkBox.loc = [];
	 $scope.fnameFilter = function(item) {
		 var filtername = [];
         if($scope.nameChkBox.loc.isNull()) return true;
       //  var stps=$scope.locChkBox.loc.length-1;
         for (var i = 0; i < $scope.nameChkBox.loc.length; i++) {
        	 if($scope.nameChkBox.loc[i]!=='empty' || $scope.nameChkBox.loc[i]!=='undefined'){
        	     if (item.AirlineName === $scope.nameChkBox.loc[i]){
        	               return true;
        		 } 
        	 }
      
         }
         return false;
       };
       
       $scope.timelist=['Before 6AM','6AM-12 Noon','12 Noon-6PM','After 6PM'];
       $scope.atimeChkBox = {};
       $scope.atimeChkBox.loc = [];
    
  	 $scope.arrivtimeFilter = function(item) {
  		 var filtername = [];
           if($scope.atimeChkBox.loc.isNull()) return true;
           for (var i = 0; i < $scope.atimeChkBox.loc.length; i++) {
        	   console.log($scope.atimeChkBox.loc[i]);
          	 if($scope.atimeChkBox.loc[i]!=='empty' ){
          		 if($scope.atimeChkBox.loc[i]!==undefined){
          		 console.log($scope.atimeChkBox.loc[i]);
          	     if (i==0){
          	    	 var date="06:00:00";
          	    	
          	    	 if(date.toString()>=item.FlightSegments[0].ArrivalTime.toString()){
          	    		  return true;
          	    	 }
          	             
          		 } 
          	   if (i==1){
        	    	 var date1="06:00:00";
        	    	 var date2="12:00:00";
        	    	 if(date1.toString()<=item.FlightSegments[0].ArrivalTime.toString()){
        	    		 if(date2.toString()>=item.FlightSegments[0].ArrivalTime.toString()){
        	    		  return true;
        	    		 }
        	    	 }
        	             
        		 }
          	 if (i==2){
    	    	 var date1="12:00:00";
    	    	 var date2="18:00:00";
    	    	 if(date1.toString()<=item.FlightSegments[0].ArrivalTime.toString()){
    	    		 if(date2.toString()>=item.FlightSegments[0].ArrivalTime.toString()){
    	    		  return true;
    	    		 }
    	    	 }
    	             
    		 } if (i==3){
    	    	 var date1="18:00:00";
    	    	 if(date1.toString()<=item.FlightSegments[0].ArrivalTime.toString()){
    	    		  return true;
    	    		 
    	    	 }
    	             
    		 }
          		 }
          	 }
           }
          
           return false;
         };
         
         $scope.detimeChkBox = {};
         $scope.detimeChkBox.loc = [];
      
    	 $scope.depertimeFilter = function(item) {
    		 var filtername = [];
             if($scope.detimeChkBox.loc.isNull()) return true;
             for (var i = 0; i < $scope.detimeChkBox.loc.length; i++) {
          	   console.log($scope.detimeChkBox.loc[i]);
            	 if($scope.detimeChkBox.loc[i]!=='empty' ){
            		 if($scope.detimeChkBox.loc[i]!==undefined){
            		 console.log($scope.detimeChkBox.loc[i]);
            	     if (i==0){
            	    	 var date="06:00:00";
            	    	
            	    	 if(date.toString()>=item.FlightSegments[0].DepartureTime.toString()){
            	    		  return true;
            	    	 }
            	             
            		 } 
            	   if (i==1){
          	    	 var date1="06:00:00";
          	    	 var date2="12:00:00";
          	    	 if(date1.toString()<=item.FlightSegments[0].DepartureTime.toString()){
          	    		 if(date2.toString()>=item.FlightSegments[0].DepartureTime.toString()){
          	    		  return true;
          	    		 }
          	    	 }
          	             
          		 }
            	 if (i==2){
      	    	 var date1="12:00:00";
      	    	 var date2="18:00:00";
      	    	 if(date1.toString()<=item.FlightSegments[0].DepartureTime.toString()){
      	    		 if(date2.toString()>=item.FlightSegments[0].DepartureTime.toString()){
      	    		  return true;
      	    		 }
      	    	 }
      	             
      		 } if (i==3){
      	    	 var date1="18:00:00";
      	    	 if(date1.toString()<=item.FlightSegments[0].DepartureTime.toString()){
      	    		  return true;
      	    		 
      	    	 }
      	             
      		 }
            		 }
            	 }
             }
            
             return false;
           };
         
       
       Array.prototype.isNull = function (){
           return this.join().replace(/,/g,'').length === 0;
       };
       
       
   /*    $scope.toggleCurrency = function(index) {
           $scope.filterstop[index].checked = !$scope.filterstop[index].checked;
           if (!$scope.filterstop[index].checked) {
             $scope.selectAll = false;
           }
         };
	 */
	 
	 $scope.flightsection1change = function(details,flightdetail,requestDetails){
 		/*$scope.requestDetails = requestDetails;
 		$scope.sourceDetail = requestDetails.source.split(',');
		$scope.destinationDetail = requestDetails.destination.split(',');
			*/
			$scope.flightsection = details;
			/*console.log($scope.flightsection);
			$scope.flights=flights;
			$scope.flight=requestDetails;*/
 		
 	};
 	$scope.flightsection2change = function(details,flightdetail,requestDetails){

 		/*$scope.requestDetails = requestDetails;
 		$scope.sourceDetail = requestDetails.source.split(',');
		$scope.destinationDetail = requestDetails.destination.split(',');*/
			$scope.flightsection2 = details;
			/*console.log($scope.flightsection2);
			$scope.flights=flights;
			$scope.flight=requestDetails;*/
 	};
	 
	 
	 $scope.getTax = function(detail,request){
		 $scope.loader = true;
		
		 request.adult=parseInt(request.adult);
		 request.child=parseInt(request.child);
		 request.infant=parseInt(request.infant);
 		 $scope.inputJson = {
 		     "requestDetails" : request,
 			 "searchDetails" : detail,
 			 "UserTrackId"  : $scope.UserTrackId
 		 }
 		 
 		 console.log($scope.inputJson);
 		 var source = request.source.split(',');
 	     var destination = request.destination.split(',');
 		 if(source[2]=='India' && destination[2]=='India' ){
 		$http.post('GetTax',$scope.inputJson).success(function(data){
 			 $scope.loader = false;
 			 console.log(data);
 				 if(data.status == "1"){
 					 showDiv('div3');
 					 console.log(data);
 					 $scope.result = data;
 					 $scope.sourceDetail = data.requestDetails.source.split(',');
		    			 $scope.destinationDetail = data.requestDetails.destination.split(',');
 					 $scope.loader=false;
 					$scope.getMealAndBaggageInfo();
 					 if(data.requestDetails.adult!= null || data.requestDetails.adult!='' || data.requestDetails.adult!= undefined ){
 						//$scope.adultNumber=parseInt(data.requestDetails.adult); 
 						for(var i = 0 ; i<parseInt(data.requestDetails.adult);i++){
 						//	 console.log(i);
 							$scope.adultNumber.push(i+1);
 							 
 						//	$scope.ticket.adult_no=data.requestDetails.adult;
 						}
 					//	console.log($scope.adultNumber);
 					 }
                      if(data.requestDetails.child!= null || data.requestDetails.child!='' || data.requestDetails.child!= undefined ){
                     	 for(var i = 0 ; i<parseInt(data.requestDetails.child);i++){
  							$scope.childNumber.push(i+1);
  							//$scope.ticket.child_no=data.requestDetails.child;
  						}
 					 }
                      if(data.requestDetails.infant!= null || data.requestDetails.infant!='' || data.requestDetails.infant!= undefined ){
                     	 for(var i = 0 ; i<parseInt(data.requestDetails.infant);i++){
  							$scope.infantNumber.push(i+1);
  						//	$scope.ticket.infant_no=data.requestDetails.infant;
  						}
 					 }
 				 }
 			 
 		}).error(function(data){
 			$scope.loader=false;
 			$scope.showAlert("danger", "Technical Error. Please try after sometime");
 		});
 		
 		 }else{
 			 //GetTaxInFSC GetTaxIn
 				$http.post('GetTaxIn',$scope.inputJson).success(function(data){
 					 $scope.loader = false;
 					 if(data.status === "1"){
 						 /*$scope.loader=false;
 						 sshowDiv('sdiv104');
 						 console.log(data);
 						 $scope.resultfsc = data;
 						 $scope.sourceDetailfsc = data.requestDetails.requestDetails.source.split(',');
	 		    			 $scope.destinationDetailfsc = data.requestDetails.requestDetails.destination.split(',');*/
	    					 showDiv('div3');
	    					 console.log(data);
	    					 $scope.result = data;
	    					 $scope.sourceDetail = data.requestDetails.requestDetails.source.split(',');
	 		    			 $scope.destinationDetail = data.requestDetails.requestDetails.destination.split(',');
	    					 $scope.loader=false;
	    					 if(data.requestDetails.requestDetails.adult!= null || data.requestDetails.requestDetails.adult!='' || data.requestDetails.requestDetails.adult!= undefined ){
	    						//$scope.adultNumber=parseInt(data.requestDetails.adult); 
	    						for(var i = 0 ; i<parseInt(data.requestDetails.requestDetails.adult);i++){
	    							$scope.adultNumberIn.push(i+1);
	    							$scope.ticket.adult_no=data.requestDetails.requestDetails.adult;
	    						}
	    					 }
                          if(data.requestDetails.requestDetails.child!= null || data.requestDetails.requestDetails.child!='' || data.requestDetails.requestDetails.child!= undefined ){
                         	 for(var i = 0 ; i<parseInt(data.requestDetails.requestDetails.child);i++){
                         		$scope.childNumberIn.push(i+1);
	    							$scope.ticket.child_no=data.requestDetails.requestDetails.child;
	    						}
	    					 }
                          if(data.requestDetails.requestDetails.infant!= null || data.requestDetails.requestDetails.infant!='' || data.requestDetails.requestDetails.infant!= undefined ){
                         	 for(var i = 0 ; i<parseInt(data.requestDetails.requestDetails.infant);i++){
                         		 $scope.infantNumberIn.push(i+1);
	    							$scope.ticket.infant_no=data.requestDetails.requestDetails.infant;
	    						}
	    					 }
	    				 }
 					
 					
 				}).error(function(data){
 	    			$scope.loader=false;
 	    			$scope.showAlert("danger", "Technical Error. Please try after sometime");
 	    		});
 	    		
 		 }
 	};
 	
 	$scope.adultNumber = [];
 	$scope.childNumber = [];
 	$scope.infantNumber = [];
	 $scope.getTaxround = function(detail,details2,request){
			 $scope.loader = true;
	 		 $scope.inputJson = {
	 		     "requestDetails" : request,
	 			 "searchDetails" : detail,
	 			"returnDetail" : details2,
	 			 "UserTrackId"  : $scope.UserTrackId
	 		 }
	 		 
	 		 console.log($scope.inputJson);
	 		 var source = request.source.split(',');
	 	     var destination = request.destination.split(',');
	 		 if(source[2]=='India' && destination[2]=='India' ){
	 		$http.post('getTaxroundtrip',$scope.inputJson).success(function(data){
	 			 $scope.loader = false;
	 			 console.log(data);
	 				 if(data.status == "1"){
	 					 showDiv('div3');
	 					 console.log(data);
	 					 $scope.result = data;
	 					 $scope.sourceDetail = data.requestDetails.source.split(',');
			    			 $scope.destinationDetail = data.requestDetails.destination.split(',');
	 					 $scope.loader=false;
	 					$scope.getMealAndBaggageInfo();
	 					// $scope.getSeatRequest();
	 					 if(data.requestDetails.adult!= null || data.requestDetails.adult!='' || data.requestDetails.adult!= undefined ){
	 						//$scope.adultNumber=parseInt(data.requestDetails.adult); 
	 						for(var i = 0 ; i<parseInt(data.requestDetails.adult);i++){
	 							// console.log(i);
	 							$scope.adultNumber.push(i+1);
	 							 
	 						//	$scope.ticket.adult_no=data.requestDetails.adult;
	 						}
	 						//console.log($scope.adultNumber);
	 					 }
	                      if(data.requestDetails.child!= null || data.requestDetails.child!='' || data.requestDetails.child!= undefined ){
	                     	 for(var i = 0 ; i<parseInt(data.requestDetails.child);i++){
	  							$scope.childNumber.push(i+1);
	  							//$scope.ticket.child_no=data.requestDetails.child;
	  						}
	 					 }
	                      if(data.requestDetails.infant!= null || data.requestDetails.infant!='' || data.requestDetails.infant!= undefined ){
	                     	 for(var i = 0 ; i<parseInt(data.requestDetails.infant);i++){
	  							$scope.infantNumber.push(i+1);
	  						//	$scope.ticket.infant_no=data.requestDetails.infant;
	  						}
	 					 }
	 				 }
	 			 
	 		}).error(function(data){
	 			$scope.loader=false;
	 			$scope.showAlert("danger", "Technical Error. Please try after sometime");
	 		});
	 		
	 		 }else{
	 			 //GetTaxInFSC GetTaxIn
	 				$http.post('GetTaxIn',$scope.inputJson).success(function(data){
	 					 $scope.loader = false;
	 					 if(data.status == "1"){
	 						 /*$scope.loader=false;
	 						 sshowDiv('sdiv104');
	 						 console.log(data);
	 						 $scope.resultfsc = data;
	 						 $scope.sourceDetailfsc = data.requestDetails.requestDetails.source.split(',');
		 		    			 $scope.destinationDetailfsc = data.requestDetails.requestDetails.destination.split(',');*/
		    					 showDiv('div3');
		    					 console.log(data);
		    					 $scope.result = data;
		    					 $scope.sourceDetail = data.requestDetails.requestDetails.source.split(',');
		 		    			 $scope.destinationDetail = data.requestDetails.requestDetails.destination.split(',');
		    					 $scope.loader=false;
		    					 if(data.requestDetails.requestDetails.adult!= null || data.requestDetails.requestDetails.adult!='' || data.requestDetails.requestDetails.adult!= undefined ){
		    						//$scope.adultNumber=parseInt(data.requestDetails.adult); 
		    						for(var i = 0 ; i<parseInt(data.requestDetails.requestDetails.adult);i++){
		    							$scope.adultNumberIn.push(i+1);
		    							$scope.ticket.adult_no=data.requestDetails.requestDetails.adult;
		    						}
		    					 }
	                          if(data.requestDetails.requestDetails.child!= null || data.requestDetails.requestDetails.child!='' || data.requestDetails.requestDetails.child!= undefined ){
	                         	 for(var i = 0 ; i<parseInt(data.requestDetails.requestDetails.child);i++){
	                         		$scope.childNumberIn.push(i+1);
		    							$scope.ticket.child_no=data.requestDetails.requestDetails.child;
		    						}
		    					 }
	                          if(data.requestDetails.requestDetails.infant!= null || data.requestDetails.requestDetails.infant!='' || data.requestDetails.requestDetails.infant!= undefined ){
	                         	 for(var i = 0 ; i<parseInt(data.requestDetails.requestDetails.infant);i++){
	                         		 $scope.infantNumberIn.push(i+1);
		    							$scope.ticket.infant_no=data.requestDetails.requestDetails.infant;
		    						}
		    					 }
		    				 }
	 					
	 					
	 				}).error(function(data){
	 	    			$scope.loader=false;
	 	    			$scope.showAlert("danger", "Technical Error. Please try after sometime");
	 	    		});
	 	    		
	 		 }
	 	};
	 	
	 
	 	
	 	
	 	 $scope.getMealAndBaggageInfo = function(){
	 		
	 		$http.post('getMealAndBaggageInfo',$scope.result).success(function(data){
	 			 console.log(data);
	 			 if(data.status=="1"){
	 				 $scope.mealbag=data.meals.SSRSegments;
	 					
	    			for(var i=0;i<$scope.mealbag.length;i++){
	    				if($scope.mealbag[i].TripIndicator==1){
	    					$scope.mealtrip1=$scope.mealbag[i].Meals;
	    					 console.log($scope.mealtrip1);
	    					$scope.baggagetrip1=$scope.mealbag[i].Baggages;
	    					 console.log($scope.baggagetrip1);
	    				}
	    				if($scope.mealbag[i].TripIndicator==2){
	    					$scope.mealtrip2=$scope.mealbag[i].Meals;
	    					$scope.baggagetrip2=$scope.mealbag[i].Baggages;
	    				}
	    			}
	    				
	 				 
	 		}else{
	 			
	 		}
	 		}).error(function(data){
	    			$scope.loader=false;
 	    			$scope.showAlert("danger", "Technical Error. Please try after sometime");
 	    		});
 	    		
	 		 
	 	 }
	 	 
	 	$scope.selectedseats = [];
        $scope.selectedfares = [];
        $scope.selectedseatdetail = [];
        $scope.tatalFare = 0.0;
        $scope.selectSeat= function(detail){
        	console.log(detail);
        	if($scope.selectedseats.includes(detail.SeatNo)){
        		var seatfaredeatail = detail.SeatNo+'|'+detail.Fare;
        		var seat=detail.SeatNo+'|'+detail.SeatTypeId;
        		$scope.selectedseats.pop(detail.SeatNo);
        		if($scope.selectedfares.includes(seatfaredeatail)){
        			$scope.selectedfares.pop(seatfaredeatail);
        			$scope.tatalFare = $scope.tatalFare - (detail.Fare); 
        		}
        		if($scope.selectedseatdetail.includes(seat)){
        			$scope.selectedseatdetail.pop(seat);
        			
        		}
        		
        	}else{
        		if($scope.selectedseats.length<6){
        		var seatfaredeatail = detail.SeatNo+'|'+detail.Fare;
        		var seat=detail.SeatNo+'|'+detail.SeatTypeId;
        		$scope.selectedseats.push(detail.SeatNo);
        		$scope.selectedfares.push(seatfaredeatail);
        		$scope.selectedseatdetail.push(seat);
        		$scope.tatalFare = $scope.tatalFare+detail.Fare;
        	}
        	}
        	console.log($scope.tatalFare);
        	console.log($scope.selectedseats);
        	console.log($scope.selectedfares);
        	
        };
        
        
        
		 $scope.getSeatRequest = function(){
			 $http.post('getSeatRequest',$scope.result).success(function(data){
				 console.log(data);
				 $scope.seatreq=data.seats.FlightSegments;
				 $scope.totalpassenger=data.totalpassenger;
			 }).error(function(data){
	    			$scope.loader=false;
 	    			$scope.showAlert("danger", "Technical Error. Please try after sometime");
 	    		});
		 }
		 
		 $scope.selectedseats = [];
	        $scope.selectedfares = [];
	        $scope.selectedseatdetail = [];
	        $scope.tatalFare = 0.0;
	        
	        $scope.clickseat = function(detail){			
	        	console.log(detail);
	        	if($scope.selectedseats.includes(detail.SeatCode)){
	        		var seatfaredeatail = detail.SeatCode+'|'+detail.SeatAmount;
	        		var seat=detail.SeatCode+'|'+detail.SeatNo+'|'+detail.status+'|'+detail.Deck+'|'+detail.Compartment+'|'+detail.WayType+'|'+detail.FlightNumber+'|'+detail.AirlineCode+'|'+detail.CraftType+'|'+detail.Origin+'|'+detail.Destination+'|'+detail.SeatAmount;
	        		$scope.selectedseats.pop(detail.SeatCode);
	        		if($scope.selectedfares.includes(seatfaredeatail)){
	        			$scope.selectedfares.pop(seatfaredeatail);
	        			$scope.tatalFare = $scope.tatalFare - (detail.SeatAmount); 
	        		}
	        		
	        		if($scope.selectedseatdetail.includes(seat)){
	        			$scope.selectedseatdetail.pop(seat);        			
	        		}
	        		
	        	}else{
	        		if($scope.selectedseats.length<$scope.totalpassenger){
	        		var seatfaredeatail = detail.SeatCode+'|'+detail.SeatAmount;
	        		var seat=detail.SeatCode+'|'+detail.SeatNo+'|'+detail.status+'|'+detail.Deck+'|'+detail.Compartment+'|'+detail.WayType+'|'+detail.FlightNumber+'|'+detail.AirlineCode+'|'+detail.CraftType+'|'+detail.Origin+'|'+detail.Destination+'|'+detail.SeatAmount;
	        		$scope.selectedseats.push(detail.SeatCode);
	        		$scope.selectedfares.push(seatfaredeatail);
	        		$scope.selectedseatdetail.push(seat);
	        		$scope.tatalFare = $scope.tatalFare+detail.SeatAmount;
	        	}
	        	}
	        	console.log($scope.tatalFare);
	        	console.log($scope.selectedseats);
	        	console.log($scope.selectedfares);
		 }

	 	 
	 	 
	 	$scope.adulttitle = [];
    	$scope.adultfname = [];
    	$scope.adultlname = [];
    	$scope.adultLmeal=[];
    	$scope.adultLmeal2=[];
    	$scope.adultLbag=[];
    	$scope.adultLbag2=[];
    	$scope.adultDOB = [];
    	$scope.adultdetail = {};
    	$scope.childdetail = {};
    	$scope.childtitle = [];
    	$scope.childfname = [];
    	$scope.childlname = [];
    	$scope.childlDob = [];
    	$scope.childLmeal=[];
    	$scope.childLmeal2=[];
    	$scope.childLbag=[];
    	$scope.childLbag2=[];
    	$scope.infantDOB=[];
    	
	 	 $scope.bookingFlight = function(details){
	 		$scope.loader=true;
	 		console.log(details);
	 		if(details===undefined|| details === null ||details === ""){
	 			$scope.loader=false;
 				$scope.showAlert("danger", "Enter mobile No Properly");
 				return false;
	 		}
	 		 if(details.mobileNo === undefined || details.mobileNo === null ||details.mobileNo === ""){
	 				$scope.loader=false;
	 				$scope.showAlert("danger", "Enter mobile No Properly");
	 				return false;
	 			}
	 		if(details.emailid === undefined || details.emailid === null ||details.emailid === ""){
 				$scope.loader=false;
 				$scope.showAlert("danger", "Enter email id Properly");
 				return false;
 			}
	 		if(details.city === undefined || details.city === null ||details.city === ""){
 				$scope.loader=false;
 				$scope.showAlert("danger", "Enter city Properly");
 				return false;
 			}
	 		
	 		if($scope.result.adultcount!= null || $scope.result.adultcount!='' || $scope.result.adultcount!= undefined ){
				for(var i = 1 ; i<=parseInt($scope.result.adultcount);i++){
						var adultTitle = document.getElementById('adulttitle'+i).value;
	    				var adultFName = document.getElementById('adultfname'+i).value;
	    				var adultLName = document.getElementById('adultlname'+i).value;
	    				var adultLMeal = document.getElementById('adult'+i+'meal1').value.trim();
	    				var adultdob = document.getElementById('adultdob'+i).value.trim();
	    				console.log(adultdob);
	    				var adultLBaggage = document.getElementById('adult'+i+'baggage').value.trim();
	    				console.log(adultLMeal);
	    				if($scope.flight.type=="R"){
	    					var adultLMeal2 = document.getElementById('adult'+i+'meal2').value.trim();
		    				console.log(adultLMeal2);
		    				var adultLBaggage2 = document.getElementById('adult'+i+'baggage2').value.trim();
		    				$scope.adultLmeal2.push(adultLMeal2);
		    				$scope.adultLbag2.push(adultLBaggage2);
	    				}
	    				
	    			
	    				$scope.adulttitle.push(adultTitle);
	    				$scope.adultfname.push(adultFName);
	    				$scope.adultlname.push(adultLName);
	    				$scope.adultDOB.push(adultdob);
	    				$scope.adultLmeal.push(adultLMeal);
	    				$scope.adultLbag.push(adultLBaggage);
	    				
					}
					$scope.adultdetail = {
	    					adulttitle : $scope.adulttitle,
	    					adultfname : $scope.adultfname,
	    					adultlname : $scope.adultlname,
	    					adultLMeal : $scope.adultLmeal,
	    					adultLMeal2 : $scope.adultLmeal2,
	    					adultLBaggage : $scope.adultLbag,
	    					adultLBaggage2 : $scope.adultLbag2,
	    					adultDOB:$scope.adultDOB
					}
			 }
	 		
	 		if($scope.result.childcount!= null || $scope.result.childcount!='' || $scope.result.childcount!= undefined ){
				for(var i = 1 ; i<=parseInt($scope.result.childcount);i++){
					var childTitle = document.getElementById('childtitle'+i).value;
    				var childFName = document.getElementById('childfname'+i).value;
    				var childLName = document.getElementById('childlname'+i).value;
    				var childMeal = document.getElementById('child'+i+'meal').value.trim();
    				var childdob=document.getElementById('childdob'+i).value.trim();
    				var childBaggage = document.getElementById('child'+i+'baggage').value.trim();
    				if($scope.flight.type=="R"){
    					var childMeal2 = document.getElementById('child'+i+'meal2').value.trim();
    				var childBaggage2 = document.getElementById('child'+i+'baggage2').value.trim();
    				$scope.childLbag2.push(childBaggage2);
    				$scope.childLmeal2.push(childMeal2);
    				}
	    				$scope.childtitle.push(childTitle);
	    				$scope.childfname.push(childFName);
	    				$scope.childlname.push(childLName);
	    				$scope.childlDob.push(childdob);
	    				$scope.childLmeal.push(childMeal);
	    				$scope.childLbag.push(childBaggage);
	    				
					}
				$scope.childdetail = {
            			childtitle : $scope.childtitle,
            			childfname : $scope.childfname,
            			childlname : $scope.childlname, 
            			childlDob:$scope.childlDob,
            			childLmeal : $scope.childLmeal,
            			childLmeal2 : $scope.childLmeal2,
            			childLbag : $scope.childLbag,
            			childLbag2 : $scope.childLbag2
					}
			 }
	 		
	 		if($scope.result.infantcount!= null || $scope.result.infantcount!='' || $scope.result.infantcount!= undefined ){
				for(var i = 1 ; i<=parseInt($scope.result.infantcount);i++){
						var infantTitle = document.getElementById('infanttitle'+i).value;
	    				var infantFName = document.getElementById('infantfname'+i).value;
	    				var infantLName = document.getElementById('infantlname'+i).value;
	    				var infantdob=document.getElementById('infantdob'+i).value.trim();
	    				$scope.infanttitle.push(infantTitle);
	    				$scope.infantfname.push(infantFName);
	    				$scope.infantlname.push(infantLName);
	    				$scope.infantDOB.push(infantdob);
					}
				$scope.infantdetail = {
               			"infanttitle" : $scope.infanttitle,
               			"infantfname" : $scope.infantfname,
               			"infantlname" : $scope.infantlname,
               			"infantDOB" : $scope.infantDOB
					}
			 }
	 		
	 		$scope.passengerdetail = {
    				"adultdetail" :	$scope.adultdetail,
    				"childdetail" :   $scope.childdetail,
                    "infantdetail" :  $scope.infantdetail
    				
    		}
	 		$scope.inputJson = {
	    		     "detail" : details,
	    			 "prevrecord" : $scope.result,
	    			 "passengerdetail" : $scope.passengerdetail,
	    			 "mealbag":$scope.mealbag
	    		}
	 		
	 		 console.log($scope.inputJson);
	 		$http.post('bookingFlight',$scope.inputJson).success(function(data){
	 			 console.log(data);
	 			$scope.loader=false;
	 			 if(data.status=="1"){
	 				$scope.adulttitle = [];
		 	    	$scope.adultfname = [];
		 	    	$scope.adultlname = [];
		 	    	$scope.adultLmeal=[];
		 	    	$scope.adultLmeal2=[];
		 	    	$scope.adultLbag=[];
		 	    	$scope.adultLbag2=[];
		 	    	$scope.adultDOB = [];
		 	    	$scope.adultdetail = {};
		 	    	$scope.childdetail = {};
		 	    	$scope.childtitle = [];
		 	    	$scope.childfname = [];
		 	    	$scope.childlname = [];
		 	    	$scope.childlDob = [];
		 	    	$scope.childLmeal=[];
		 	    	$scope.childLmeal2=[];
		 	    	$scope.childLbag=[];
		 	    	$scope.childLbag2=[];
		 	    	$scope.infantDOB=[];
		 	    	sshowDiv('div0');
		 	    	$scope.showAlert("success", data.message);
	 		}else{
	 			$scope.adulttitle = [];
	 	    	$scope.adultfname = [];
	 	    	$scope.adultlname = [];
	 	    	$scope.adultLmeal=[];
	 	    	$scope.adultLmeal2=[];
	 	    	$scope.adultLbag=[];
	 	    	$scope.adultLbag2=[];
	 	    	$scope.adultDOB = [];
	 	    	$scope.adultdetail = {};
	 	    	$scope.childdetail = {};
	 	    	$scope.childtitle = [];
	 	    	$scope.childfname = [];
	 	    	$scope.childlname = [];
	 	    	$scope.childlDob = [];
	 	    	$scope.childLmeal=[];
	 	    	$scope.childLmeal2=[];
	 	    	$scope.childLbag=[];
	 	    	$scope.childLbag2=[];
	 	    	$scope.infantDOB=[];
	 	    	$scope.showAlert("danger", data.message);
	 		}
	 		}).error(function(data){
	    			$scope.loader=false;
 	    			$scope.showAlert("danger", "Technical Error. Please try after sometime");
 	    		});
 	    		
	 		 
	 	 }
	 	 
	 	 
	/* 	 $scope.locChkBox = {};
	     $scope.locChkBox.loc = [];
		 $scope.stopFilter = function(item) {
			 var filterstop = [];
	         if($scope.locChkBox.loc.isNull()) return true;
	       //  var stps=$scope.locChkBox.loc.length-1;
	         for (var i = 0; i < $scope.locChkBox.loc.length; i++) {
	        	 if($scope.locChkBox.loc[i]!=='empty' || $scope.locChkBox.loc[i]!=='undefined'){
	        	     if (item.stops === parseInt($scope.locChkBox.loc[i])){
	        	               return true;
	        		 } 
	        	 }
	      
	         }
	         return false;
	       };*/
	 	 
	 	 
	 	$scope.pasengrs = {};
	     $scope.pasengrs.loc = [];
	 	$scope.cancelTicket = function(details,passengr){
	 		console.log(details);
	 		console.log(passengr);
	 		$scope.ctiket={"PNRDT":details,
	 				"passengr":passengr};
	 		console.log($scope.ctiket);	
			 $http.post('cancelTicket',$scope.ctiket).success(function(data){
				 console.log(data);
				 $scope.seatreq=data.seats.FlightSegments;
				 $scope.totalpassenger=data.totalpassenger;
			 }).error(function(data){
	    			$scope.loader=false;
	    			$scope.showAlert("danger", "Technical Error. Please try after sometime");
	    		});
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

