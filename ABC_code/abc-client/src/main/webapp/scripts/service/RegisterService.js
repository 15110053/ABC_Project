angular.module("EcommerceModule").factory("RegisterService", function($http){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	var httpPostData = function(URL, data){
		return $http.post(URL, data);
	}
	returnValue.register = function(data){
		return httpPostData(BASEURL + "Register", data);
	}
	return returnValue;
});