angular.module("EcommerceModule").controller("FailOrderProducerController", function ($scope, $mdDialog, OrderPageProducerService){
	$scope.productData;	
	$scope.orderData = [];
	var getData = function(callback){
		OrderPageProducerService.getFailOrderDetail().then(
			function(response){
				$scope.productData = response.data.object;
				callback();
			},function(error){
				console.log(error);
			}
		)
	}
	var getOrderData = function(){
		if($scope.productData === null || $scope.productData === undefined || $scope.productData.length === 0)
			return;
		var orderDataIndex=1;
		$scope.orderData.push($scope.productData[0].order);
		$scope.orderData[0].statusDTO = $scope.productData[0].statusDTO;
		for(var i = 1; i < $scope.productData.length; i++){
			if($scope.orderData[orderDataIndex-1].idOrder !== $scope.productData[i].order.idOrder){
				$scope.orderData.push($scope.productData[i].order);
				$scope.orderData[orderDataIndex].statusDTO = $scope.productData[i].statusDTO;
				orderDataIndex++;
				
			}
		}
	}
	var searchOrderDetailByOrder = function(orderId){
		var orderDetail = [];
		for(var i=0; i<$scope.productData.length; i++){
			if($scope.productData[i].order.idOrder == orderId){
				orderDetail.push($scope.productData[i]);
			}
		}
		return orderDetail;
	}
	this.openDialog = function(idOrder, ev){
		var orderDetail = searchOrderDetailByOrder(idOrder);
		$mdDialog.show({
			locals: { orderData: orderDetail},
			controller: 'OrderDetailProducerDialogController',
			controllerAs: "ctrl",
			templateUrl: 'views/orderdetaildialog.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true,
		})
	}
	getData(getOrderData);
});
