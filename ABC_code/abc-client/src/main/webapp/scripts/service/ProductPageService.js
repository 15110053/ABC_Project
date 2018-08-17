angular.module("EcommerceModule").factory("ProductpageService", function($http){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	returnValue.httpGetData = function(URL, data){
		return $http.get(URL, data);
	}
	var httpGetDataWithParam = function(URL, config){
		return $http.get(URL, config);
	}
	returnValue.httpGetProduct = function(id){
		var param = {idProduct: id};
		return httpGetDataWithParam(BASEURL + "Product/GetProduct", {params: param});
	}
	return returnValue;
})