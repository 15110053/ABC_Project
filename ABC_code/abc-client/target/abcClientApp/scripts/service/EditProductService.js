angular.module("EcommerceModule").factory("EditProductService", function($http){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	returnValue.editProduct = function(product, token){
		return $http.post(BASEURL + "ModifyProduct/EditProduct?token="+ token, product);
	}
	returnValue.addProduct = function(product, token){
		return $http.post(BASEURL + "ModifyProduct/AddProduct?token="+ token, product);
	}
	return returnValue;
});
