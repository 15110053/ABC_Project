angular.module("EcommerceModule").controller("ManagementProductController", function($scope, $mdDialog, $state, $cookies, ManagementProductService){
	$scope.productData;
	var userInfo = $cookies.getObject("token")[0];
	this.getProduct = function(){
		ManagementProductService.getProduct(userInfo.userId, $cookies.getObject("token")[1]).then(
			function(response){
				$scope.productData = response.data.object;
				if(response.data.status == 1){
					$state.go("main");
					$mdDialog.show(
						$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('not permission')
						.ariaLabel('Alert Dialog')
						.ok('Got it')
					)
				}
			}, function(error){
				console.log(error);
			})
	}

	this.showEditDialog = function(id){
		$state.go("editProduct", {"id": id});
	}
	this.showAddDialog = function(){
		$state.go("addProduct");
	}
	this.getProduct();
});