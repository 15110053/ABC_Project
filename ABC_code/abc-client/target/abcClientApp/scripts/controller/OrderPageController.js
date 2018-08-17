angular.module("EcommerceModule").controller("OrderPageController", function ($scope, $cookies, $window, OrderPageService, $mdDialog){
	$scope.productData = $cookies.getObject("cart");
	$scope.totalPrice = 0;
	$scope.selectedIndex=0;
	$scope.step1Status= true;
	$scope.step2Status= false;
	$scope.step3Status= false;
	$scope.fullName;
	$scope.phoneNumber;
	$scope.address;
	$scope.payment;
	var checkHasProductInCart = function(){
		if($scope.productData.length == undefined)
			return false;
		return true;
	}
	var calculateTotalPrice = function(){
		$scope.totalPrice = 0;
		if($scope.productData === undefined )
			return;
		for(var i = 0; i < $scope.productData.length; i++){
			$scope.totalPrice += $scope.productData[i].quantity * $scope.productData[i].product.price;
		}
	}
	this.openTab = function(step){
		if(step == "3" && checkStep2Filled() == true){
			$scope.selectedIndex=2;
			$scope.step1Status= false;
			$scope.step2Status= false;
			$scope.step3Status= true;
		}else if(step == "2"){
			$scope.selectedIndex=1;
			$scope.step1Status= false;
			$scope.step2Status= true;
			$scope.step3Status= false;
		}else if(step == "1"){
			$scope.selectedIndex=0;
			$scope.step1Status= true;
			$scope.step2Status= false;
			$scope.step3Status= false;
		}
	};
	var checkStep2Filled = function(){
		if($scope.fullName == null || $scope.phoneNumber == null || $scope.address == null || $scope.payment == null)
			return false;
		return true;
	}
	this.translatePaymentMethod = function(){
		if($scope.payment == 1)
			return "Cash on delivery";
	}
	var getArrayCart = function(){
		var array = [];
		for(var i=0; i<$scope.productData.length; i++){
			var product = {
				productDTO: {
					idProduct: $scope.productData[i].product.idProduct
				},
				quantity: $scope.productData[i].quantity
			}
			array.push(product);
		}
		return array;
	}
	this.createOrder = function(){
		var data = {
			orderDTO: {
				price: $scope.totalPrice,
				content: "",
				statusDTO: "0",
				fullName: $scope.fullName,
				address: $scope.address,
				phoneNumber: $scope.phoneNumber,
				consumerDTO: {
					userId: $cookies.getObject("token")[0].userId
				}
			},
			cart: getArrayCart()
		}
		OrderPageService.orderProduct(data, $cookies.getObject("token")[1]).then(
			function(response){
				console.log(response);
				if(response.data.status == "1"){
					$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('Your order is created fail, please relogin and try again')
						.ariaLabel('Notification')
						.ok('Got it')
					).then(function(){
						$cookies.remove("token");
						$window.location.href = "/abcClientApp";
					});
				}else {
					$mdDialog.show(
						$mdDialog.alert()
							.clickOutsideToClose(true)
							.title('Notification')
							.textContent('Your order has been created')
							.ariaLabel('Notification')
							.ok('Got it')
					);
				}
			}, function(error){
				$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('Your order is created fail, please relogin and try again')
						.ariaLabel('Notification')
						.ok('Got it')
				).then(function(){
					$cookies.remove("token");
					$window.location.href = "/abcClientApp";
				});
			}
		)
	}
	calculateTotalPrice();
});