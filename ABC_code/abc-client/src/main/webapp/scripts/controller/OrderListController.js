angular.module("EcommerceModule").controller("OrderListController", function ($scope, $cookies, $mdDialog, OrderPageService){
	$scope.orderData;
	this.isCancel = function(id){
		for(var i=0; i<$scope.orderData.length; i++){
			if($scope.orderData[i].idOrder == id && $scope.orderData[i].statusDTO != "NONE"){
				return true;
			}
		}
		return false;
	}
	this.cancel = function(orderid){
		$mdDialog.show(
			$mdDialog.confirm()
				.clickOutsideToClose(true)
				.title('Notification')
				.textContent('Do you want to cancel')
				.ariaLabel('Alert Dialog Demo')
				.ok('Yes')
				.cancel("Cancel")
		).then(function(){
			var data = {
				consumerDTO: {
					userId: $cookies.getObject("token")[0].userId
				},
				idOrder: orderid,
				statusDTO: 1
			}
			OrderPageService.cancelOrder(data, $cookies.getObject("token")[1]).then(
				function(response){
					if(response.data.status == 0){
						$scope.orderData = response.data.object;
						for(var i=0; i<$scope.orderData.length; i++)
							$scope.orderData[i].orderDate = new Date($scope.orderData[i].orderDate).toLocaleString('en-US', { timeZone: 'UTC' })
					}
				}, function(error){
					console.log(error);
				}
			)
		});
	}
	this.view = function(idOrder, total, ev){
		$mdDialog.show({
			locals: { orderId: idOrder,
						total: total},
			controller: 'OrderDetailDialogController',
			controllerAs: "ctrl",
			templateUrl: 'views/orderdetaildialog.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true,
		})
	}
	var getOrderList = function(){
		OrderPageService.getOrderList().then(
			function(response){
				$scope.orderData = response.data.object;
				for(var i=0; i<$scope.orderData.length; i++)
					$scope.orderData[i].orderDate = new Date($scope.orderData[i].orderDate).toLocaleString('en-US', { timeZone: 'UTC' })
			}, function(error){
				console.log(error);
			}
		);
	}
	getOrderList();
});