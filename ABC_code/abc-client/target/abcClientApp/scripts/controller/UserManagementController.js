angular.module("EcommerceModule").controller("UserManagementController", function ($scope, $state){
	this.openOrderList = function(){
		$state.go("userManagement.orderList");
	}
	this.openShoppingCart = function(){
		$state.go("userManagement.cart");
	}
	this.openProfileManagement = function(){
		$state.go("userManagement.profileManagement");
	}
});