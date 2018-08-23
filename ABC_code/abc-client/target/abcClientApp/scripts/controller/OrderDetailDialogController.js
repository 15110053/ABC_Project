angular.module("EcommerceModule").controller("OrderDetailDialogController", function ($scope, orderId, total, $mdDialog, OrderPageService){
	$scope.productData;
	$scope.id = orderId;
	$scope.total = total;
	$scope.totalProduct;
	var getData = function(callback){
		var data = {
			orderId: orderId
		}
		OrderPageService.getOrderDetail(data).then(
			function(response){
				$scope.productData = response.data.object;
				callback();
			},function(error){
				console.log(error);
			}
		)
	}
	this.close = function(){
		$mdDialog.hide();
	}
	var totalProduct = function(){
		var sum = 0;
		for(var i=0; i< $scope.productData.length; i++)
			if($scope.productData[i].statusDTO !== "FAIL")
				sum += $scope.productData[i].quantity;
		$scope.totalProduct = sum;
	}
	getData(totalProduct);
});