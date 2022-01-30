"use strict";
var app = angular.module('app',['ngSanitize','ngCookies','rzSlider'])

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

.directive('sliderRange', ['$document',function($document) {

// Move slider handle and range line
  var moveHandle = function(handle, elem, posX) {
    $(elem).find('.handle.'+handle).css("left",posX +'%');
  };
  var moveRange = function(elem,posMin,posMax) {
    $(elem).find('.range').css("left",posMin +'%');
    $(elem).find('.range').css("width",posMax - posMin +'%');
  };

return {
    template: '<div class="slider horizontal">'+
                '<div class="range"></div>'+
                '<a class="handle min" ng-mousedown="mouseDownMin($event)"></a>'+
                '<a class="handle max" ng-mousedown="mouseDownMax($event)"></a>'+
              '</div>',
    replace: true,
    restrict: 'E',
    scope:{
      valueMin:"=",
      valueMax:"="
    },
    link: function postLink(scope, element, attrs) {
        // Initilization
        var dragging = false;
        var startPointXMin = 0;
        var startPointXMax = 0;
        var xPosMin = 0;
        var xPosMax = 0;
        var settings = {
                "min"   : (typeof(attrs.min) !== "undefined"  ? parseInt(attrs.min,10) : 0),
                "max"   : (typeof(attrs.max) !== "undefined"  ? parseInt(attrs.max,10) : 100),
                "step"  : (typeof(attrs.step) !== "undefined" ? parseInt(attrs.step,10) : 1)
            };
        if ( typeof(scope.valueMin) == "undefined" || scope.valueMin === '' ) 
            scope.valueMin = settings.min;
            
        if ( typeof(scope.valueMax) == "undefined" || scope.valueMax === '' ) 
            scope.valueMax = settings.max;
            
        // Track changes only from the outside of the directive
        scope.$watch('valueMin', function() {
          if (dragging) return;
          xPosMin = ( scope.valueMin - settings.min ) / (settings.max - settings.min ) * 100;
          if(xPosMin < 0) {
              xPosMin = 0;
          } else if(xPosMin > 100)  {
              xPosMin = 100;
          }
          moveHandle("min",element,xPosMin);
          moveRange(element,xPosMin,xPosMax);
        });

        scope.$watch('valueMax', function() {
          if (dragging) return;
          xPosMax = ( scope.valueMax - settings.min ) / (settings.max - settings.min ) * 100;
          if(xPosMax < 0) {
              xPosMax = 0;
          } else if(xPosMax > 100)  {
              xPosMax = 100;
          }
          moveHandle("max",element,xPosMax);
          moveRange(element,xPosMin,xPosMax);
        });

        // Real action control is here
        scope.mouseDownMin = function($event) {
            dragging = true;
            startPointXMin = $event.pageX;
        
            // Bind to full document, to make move easiery (not to lose focus on y axis)
            $document.on('mousemove', function($event) {
                if(!dragging) return;

                //Calculate handle position
                var moveDelta = $event.pageX - startPointXMin;

                xPosMin = xPosMin + ( (moveDelta / element.outerWidth()) * 100 );
                if(xPosMin < 0) {
                    xPosMin = 0;
                } else if(xPosMin > xPosMax) {
                  xPosMin = xPosMax;
                } else {
                    // Prevent generating "lag" if moving outside window
                    startPointXMin = $event.pageX;
                }
                scope.valueMin = Math.round((((settings.max - settings.min ) * (xPosMin / 100))+settings.min)/settings.step ) * settings.step;
                scope.$apply();
                
                // Move the Handle
                moveHandle("min", element,xPosMin);
                moveRange(element,xPosMin,xPosMax);
            });
        $document.mouseup(function(){
                dragging = false;
                $document.unbind('mousemove');
                $document.unbind('mousemove');
            });
        };

        scope.mouseDownMax = function($event) {
            dragging = true;
            startPointXMax = $event.pageX;
        
            // Bind to full document, to make move easiery (not to lose focus on y axis)
            $document.on('mousemove', function($event) {
                if(!dragging) return;

                //Calculate handle position
                var moveDelta = $event.pageX - startPointXMax;

                xPosMax = xPosMax + ( (moveDelta / element.outerWidth()) * 100 );
                if(xPosMax > 100)  {
                    xPosMax = 100;
                } else if(xPosMax < xPosMin) {
                  xPosMax = xPosMin;
                } else {
                    // Prevent generating "lag" if moving outside window
                    startPointXMax = $event.pageX;
                }
                scope.valueMax = Math.round((((settings.max - settings.min ) * (xPosMax / 100))+settings.min)/settings.step ) * settings.step;
                scope.$apply();
                
                // Move the Handle
                moveHandle("max", element,xPosMax);
                moveRange(element,xPosMin,xPosMax);
            });

            $document.mouseup(function(){
                dragging = false;
                $document.unbind('mousemove');
                $document.unbind('mousemove');
            });
        };
    }
  };
}])






.controller('appController', ["$scope", "$http", "$window", "$parse", "$sce", "$cookieStore", "$filter","$location",function($scope, $http, $window,$parse,$sce, $cookieStore, $filter,$location) {
	
	$scope.showAlert = function(alertType, alertText) {		
		$scope.alertMessage = {
			type : alertType,
			text : alertText,
			closable : true,
			delay : 5
		};
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

