angular.module("EcommerceModule").factory("MainpageService", function($http){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	var httpGetData = function(URL){
		return $http.get(URL);
	}
	var httpGetDataWithParam = function(URL, config){
		return $http.get(URL, config);
	}
	var httpPost = function(URL, data){
		return $http.post(URL, data);
	}
	returnValue.httpGetCategory = function(){
		return httpGetData(BASEURL + "GetCategoryList");
	}
	returnValue.httpGetSubCategory = function(){
		return httpGetData(BASEURL + "GetSubCategoryList");
	}
	returnValue.httpGetProduct = function(){
		return httpGetData(BASEURL + "GetProductList");
	}
	returnValue.httpGetProductCount = function(){
		return httpGetData(BASEURL + "GetProductCount");
	}
	returnValue.httpGetnewProduct = function(index, maxResult){
		var param = {index: index, maxResult: maxResult};
		return httpGetDataWithParam(BASEURL + "Product/GetNewProduct", {params: param});
	}
	returnValue.httpGetMostViewProduct = function(index, maxResult){
		var param = {index: index, maxResult: maxResult};
		return httpGetDataWithParam(BASEURL + "Product/GetTheMostViewedProduct", {params: param});
	}
	returnValue.OpenCart = function(token){
		var URL = {
			url: BASEURL + "Cart/Purchase",
			headers: {
				'token': token
			}
		};
		return httpGetData(URL);
	}
	returnValue.logOut = function(token){
		var URL = BASEURL + "User/Logout?token=" + token;
		return $http.post(URL, {});
	}
	return returnValue;
})