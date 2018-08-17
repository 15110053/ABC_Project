angular.module("EcommerceModule").controller("CartController", function ($scope, $cookies, $mdDialog, CartService){
	$scope.productData = $cookies.getObject("cart");
	$scope.totalPrice = 0;
	var calculateTotalPrice = function(){
		$scope.totalPrice = 0;
		if($scope.productData === undefined )
			return;
		for(var i = 0; i < $scope.productData.length; i++){
			$scope.totalPrice += $scope.productData[i].quantity * $scope.productData[i].product.price;
		}
	}
	var findProductDataByID = function(id){
		for(var i = 0; i < $scope.productData.length; i++){
			if($scope.productData[i].product.idProduct == id)
				return i;
		}
	}
	this.plus = function(id){
		var i = findProductDataByID(id);
		$scope.productData[i].quantity += 1;
		CartService.changeValue($scope.productData);
		calculateTotalPrice();
	}
	this.minus = function(id){
		var i = findProductDataByID(id);
		if($scope.productData[i].quantity > 1)
			$scope.productData[i].quantity -= 1;
		CartService.changeValue($scope.productData);
		calculateTotalPrice();
	}
	this.remove = function(id){
		$mdDialog.show(
			$mdDialog.confirm()
				.clickOutsideToClose(true)
				.title('Notification')
				.textContent('Do you want to delete')
				.ariaLabel('Alert Dialog Demo')
				.ok('Yes')
				.cancel("Cancel")
		).then(function(){
			$scope.productData.splice(findProductDataByID(id), 1);
			CartService.changeValue($scope.productData);
			calculateTotalPrice();
		})
		
	}
	this.checkHasProductInCart = function(){
		if($cookies.getObject("cart").length == 0)
			return false;
		return true;
	}
	this.cleanCart = function(){
		$mdDialog.show(
			$mdDialog.confirm()
				.clickOutsideToClose(true)
				.title('Notification')
				.textContent('Do you want to clean')
				.ariaLabel('Alert Dialog Demo')
				.ok('Yes')
				.cancel("Cancel")
		).then(function(){
			$scope.productData = [];
			CartService.changeValue([]);
			calculateTotalPrice();
		})
	}
	calculateTotalPrice();
});
