$scope.filterValue = function($event){
	if(isNaN(String.fromCharCode($event.keyCode))){
		$event.preventDefault();
	}
};