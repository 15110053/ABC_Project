angular.module("EcommerceModule").controller("UserManagementController", function ($scope, $state){
	$scope.orderList = false;
	$scope.shoppingCart = false;
	$scope.profile = false;
	
	this.openOrderList = function(){
		$scope.orderList = true;
		$scope.shoppingCart = false;
		$scope.profile = false;
		$state.go("userManagement.orderList");
	}
	this.openShoppingCart = function(){
		$scope.orderList = false;
		$scope.shoppingCart = true;
		$scope.profile = false;
		$state.go("userManagement.cart");
	}
	this.openProfileManagement = function(){
		$scope.orderList = false;
		$scope.shoppingCart = false;
		$scope.profile = true;
		$state.go("userManagement.profileManagement");
	}
});