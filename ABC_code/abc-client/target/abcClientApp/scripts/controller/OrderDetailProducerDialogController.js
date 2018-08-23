angular.module("EcommerceModule").controller("OrderDetailProducerDialogController", function ($scope, orderData, $mdDialog){
	$scope.productData = orderData;
	$scope.totalProduct;
	$scope.total;
	
	var calculateTotal = function(){
		var totalProduct = 0;
		var total = 0;
		for(var i=0 ;i<orderData.length; i++){
			totalProduct += orderData[i].quantity;
			total += orderData[i].product.price * orderData[i].quantity;
		}
		$scope.totalProduct = totalProduct;
		$scope.total = total;
	}
	
	this.close = function(){
		$mdDialog.hide();
	}
	calculateTotal();
});