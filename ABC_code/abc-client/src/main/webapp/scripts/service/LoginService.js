angular.module("EcommerceModule").factory("LoginService", function($http){
	var returnValue = {};
	var BASE_URL = "http://localhost:8080/";
	var httpPostData = function(URL, Data){
		return $http.post(URL, Data);
	}
	returnValue.login = function(data){
		return httpPostData(BASE_URL + "Login", data);
	}
	return returnValue;
});