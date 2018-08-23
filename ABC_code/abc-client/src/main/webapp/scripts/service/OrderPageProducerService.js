angular.module("EcommerceModule").factory("OrderPageProducerService", function($http, $cookies){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	returnValue.getWaitingOrderDetail = function(){
		var data = {
			producerId: $cookies.getObject("token")[0].userId,
			token: $cookies.getObject("token")[1]
		}
		return $http.get(BASEURL + "ChangeOrderStatus/GetWaitingOrderDetail", {params: data});
	}
	returnValue.getInprocessOrderDetail = function(){
		var data = {
			producerId: $cookies.getObject("token")[0].userId,
			token: $cookies.getObject("token")[1]
		}
		return $http.get(BASEURL + "ChangeOrderStatus/GetInprocessOrderDetail", {params: data});
	}
	returnValue.getFailOrderDetail = function(){
		var data = {
			producerId: $cookies.getObject("token")[0].userId,
			token: $cookies.getObject("token")[1]
		}
		return $http.get(BASEURL + "ChangeOrderStatus/GetFailOrderDetail", {params: data});
	}
	returnValue.inprocess = function(data){
		data.product.userDTO.userId = $cookies.getObject("token")[0].userId;
		return $http.post(BASEURL + "ChangeOrderStatus/InprocessOrderDetail?token=" + $cookies.getObject("token")[1], data);
	}
	returnValue.fail = function(data){
		data.product.userDTO.userId = $cookies.getObject("token")[0].userId;
		return $http.post(BASEURL + "ChangeOrderStatus/FailOrderDetail?token=" + $cookies.getObject("token")[1], data);
	}
	return returnValue;
});
