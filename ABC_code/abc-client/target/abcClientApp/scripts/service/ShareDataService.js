angular.module("EcommerceModule").factory("ShareDataService", function(){
	var ReturnValue = {};
	var SavedData = {};
	ReturnValue.set = function(data){
		SavedData = data;
	}
	ReturnValue.get = function(){
		return SavedData;
	}
	return ReturnValue;
})