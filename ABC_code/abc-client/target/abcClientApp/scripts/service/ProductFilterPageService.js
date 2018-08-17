angular.module("EcommerceModule").factory("ProductFilterpageService", function($http){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	var httpGetData = function(URL){
		return $http.get(URL);
	}
	var httpGetDataWithParam = function(URL, config){
		return $http.get(URL, config);
	}
	returnValue.httpGetProductCount = function(){
		return httpGetData(BASEURL + "GetProductCount");
	}
	returnValue.httpGetProductByTypeCount = function(categoryId){
		var param = {categoryId: categoryId};
		return httpGetDataWithParam(BASEURL + "Product/GetProductBySubCategoryCount", {params: param});
	}
	returnValue.httpGetnewProduct = function(index, maxResult){
		var param = {index: index, maxResult: maxResult};
		return httpGetDataWithParam(BASEURL + "Product/GetNewProduct", {params: param});
	}
	returnValue.httpGetMostViewProduct = function(index, maxResult){
		var param = {index: index, maxResult: maxResult};
		return httpGetDataWithParam(BASEURL + "Product/GetTheMostViewedProduct", {params: param});
	}
	returnValue.httpGetProductById = function(categoryId, index, maxResult){
		var param = {categoryId: categoryId, index: index, maxResult: maxResult};
		return httpGetDataWithParam(BASEURL + "Product/GetProductBySubCategory", {params: param});
	}
	returnValue.httpGetProductBySearch = function(keyword, index, maxResult){
		var param = {keyWord: keyword, index: index, maxResult: maxResult};
		return httpGetDataWithParam(BASEURL + "Product/GetProductBySearch", {params: param});
	}
	returnValue.httpGetProductBySearchCount = function(keyword){
		var param = {keyWord: keyword};
		return httpGetDataWithParam(BASEURL + "Product/GetProductBySearchCount", {params: param});
	}
	returnValue.httpGetProductByCategoryCount = function(categoryId){
		var param = {categoryId: categoryId};
		return httpGetDataWithParam(BASEURL + "Product/GetProductByCategoryCount", {params: param});
	}
	returnValue.httpGetProductByCategory = function(categoryId, index, maxResult){
		var param = {categoryId: categoryId, index: index, maxResult: maxResult};
		return httpGetDataWithParam(BASEURL + "Product/GetProductByCategory", {params: param});
	}
	return returnValue;
});