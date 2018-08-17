angular.module("EcommerceModule").controller("ProducerManagementController", function ($state){
	this.openProfileManagement = function(){
		$state.go("producerManagement.profileManagement");
	}
	this.openProductManagement = function(){
		$state.go("producerManagement.managementProduct");
	}
	this.openOrderManagement = function(){
		$state.go("producerManagement.orderList");
	}
});
