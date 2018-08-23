angular.module("EcommerceModule").controller("ProducerManagementController", function ($scope, $state){
	$scope.profilePage = false;
	$scope.productPage = false;
	$scope.waitingOrderPage = false;
	$scope.inprocessOrderPage = false;
	$scope.failOrderPage = false;
	this.openProfileManagement = function($event){
		$scope.profilePage = true;
		$scope.productPage = false;
		$scope.waitingOrderPage = false;
		$scope.inprocessOrderPage = false;
		$scope.failOrderPage = false;
		$state.go("producerManagement.profileManagement");
	}
	this.openProductManagement = function(){
		$scope.profilePage = false;
		$scope.productPage = true;
		$scope.waitingOrderPage = false;
		$scope.inprocessOrderPage = false;
		$scope.failOrderPage = false;
		$state.go("producerManagement.managementProduct");
	}
	this.openWaitingOrder = function(){
		$scope.profilePage = false;
		$scope.productPage = false;
		$scope.waitingOrderPage = true;
		$scope.inprocessOrderPage = false;
		$scope.failOrderPage = false;
		$state.go("producerManagement.waitingOrderList");
	}
	this.openInprocessOrder = function(){
		$scope.profilePage = false;
		$scope.productPage = false;
		$scope.waitingOrderPage = false;
		$scope.inprocessOrderPage = true;
		$scope.failOrderPage = false;
		$state.go("producerManagement.inprocessOrderList");
	}
	this.openFailOrder = function(){
		$scope.profilePage = false;
		$scope.productPage = false;
		$scope.waitingOrderPage = false;
		$scope.inprocessOrderPage = false;
		$scope.failOrderPage = true;
		$state.go("producerManagement.failOrderList");
	}
});
