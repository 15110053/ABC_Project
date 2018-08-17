angular.module("EcommerceModule").factory("ManagementProductService", function($http){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	returnValue.getProduct = function(userID, token){
		var params = {
			userID: userID,
			token: token
		}
		return $http.get(BASEURL + "ModifyProduct/ProductManagement", {params: params});
	}
	return returnValue;
});