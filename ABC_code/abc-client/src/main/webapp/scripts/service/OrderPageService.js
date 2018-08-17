angular.module("EcommerceModule").factory("OrderPageService", function ($http, $cookies){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	returnValue.orderProduct = function(data, token){
		return $http.post(BASEURL + "Order/CreateOrder?token="+token, data);
	}
	returnValue.getOrderList = function(){
		var data = {
			userId: $cookies.getObject("token")[0].userId,
			token: $cookies.getObject("token")[1]
		}
		return $http.get(BASEURL + "Order/GetOrder", {params: data});
	}
	returnValue.cancelOrder = function(data, token){
		return $http.post(BASEURL + "Order/CancelOrder?token=" + token, data);
	}
	returnValue.getOrderDetail = function(data){
		data.token = $cookies.getObject("token")[1];
		return $http.get(BASEURL + "Order/GetOrderDetail", {params: data});
	}
	return returnValue;
});