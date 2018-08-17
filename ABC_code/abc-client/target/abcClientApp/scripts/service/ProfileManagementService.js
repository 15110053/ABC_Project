angular.module("EcommerceModule").factory("ProfileManagementService", function($http, $cookies){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/"
	returnValue.getUserInfo = function(){
		var data = {
			token: $cookies.getObject("token")[1],
			userId: $cookies.getObject("token")[0].userId
		}
		return $http.get(BASEURL + "User/GetUser", {params: data});
	}
	returnValue.updateUserInfo = function(data){
		return $http.post(BASEURL + "User/UpdateUserInfo?token=" + $cookies.getObject("token")[1], data);
	}
	return returnValue;
});