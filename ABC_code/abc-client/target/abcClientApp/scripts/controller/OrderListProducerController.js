angular.module("EcommerceModule").controller("OrderListProducerController", function ($scope, $state, $mdDialog, OrderPageProducerService){
	$scope.productData;
	$scope.orderData = [];
	var getData = function(callback){
		OrderPageProducerService.getWaitingOrderDetail().then(
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
	this.inprocess = function(orderId, index){
		$mdDialog.show(
			$mdDialog.confirm()
				.clickOutsideToClose(true)
				.title('Notification')
				.textContent('Do you want to change to in-process status')
				.ariaLabel('Alert Dialog Demo')
				.ok('Yes')
				.cancel("Cancel")
		).then(function(){
			var data = {
				order: {
					idOrder: orderId
				},
				product:{
					userDTO:{
						userId: ""
					}
				}
			}
			OrderPageProducerService.inprocess(data).then(
				function(response){
					if(response.data.status == 0)
						$scope.orderData.splice(index, 1);
					else {
						$mdDialog.show(
							$mdDialog.alert()
								.clickOutsideToClose(true)
								.title('Notification')
								.textContent("Can't change status, the order has been cancelled")
								.ariaLabel('Alert Dialog Demo')
								.ok('Yes')
						).then(function(){
							$state.reload();
						})
					}
				}, function(error){
					console.log(error);
				}
			)
		}, function(){})
	}
	this.fail = function(orderId, index){
		$mdDialog.show(
			$mdDialog.confirm()
				.clickOutsideToClose(true)
				.title('Notification')
				.textContent('Do you want to change to fail status')
				.ariaLabel('Alert Dialog Demo')
				.ok('Yes')
				.cancel("Cancel")
		).then(function(){
			var data = {
				order: {
					idOrder: orderId
				},
				product:{
					userDTO:{
						userId: ""
					}
				}
			}
			OrderPageProducerService.fail(data).then(
				function(response){
					if(response.data.status == 0)
						$scope.orderData.splice(index, 1);
					else {
						$mdDialog.show(
							$mdDialog.alert()
								.clickOutsideToClose(true)
								.title('Notification')
								.textContent("Can't change status, please reload to update data")
								.ariaLabel('Alert Dialog Demo')
								.ok('Yes')
						).then(function(){
							$state.reload();
						})
					}
				}, function(error){
					console.log(error);
				}
			)
		}, function(){})
	}
	getData(getOrderData);
});