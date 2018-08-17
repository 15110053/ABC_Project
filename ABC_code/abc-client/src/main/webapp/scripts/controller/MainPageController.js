angular.module("EcommerceModule").controller("MainPageController", function ($scope, MainpageService, CartService){
	$scope.NewProductData;
	$scope.MostViewProductData;
	var maxProductResult = 5;
	this.whiteframe = 3;
	this.hover = function(ev){
		var ele = angular.element(ev.currentTarget);
		ele.addClass("md-whiteframe-10dp");
	}
	this.leave = function(ev){
		var ele = angular.element(ev.currentTarget);
		ele.removeClass("md-whiteframe-10dp");
	}
	this.getnewProduct = function (){
		MainpageService.httpGetnewProduct(0, maxProductResult).then(
		function(response){
			$scope.NewProductData = response.data.object;
		}, function(error){
			console.log(error);
		})
	}
	this.getMostViewProduct = function (){
		MainpageService.httpGetMostViewProduct(0, maxProductResult).then(
		function(response){
			$scope.MostViewProductData = response.data.object;
		}, function(error){
			console.log(error);
		})
	}
	this.addNewProductToCart = function(id){
		CartService.addProductToCart(id, $scope.NewProductData);
	}
	this.addMostViewProductToCart = function(id){
		CartService.addProductToCart(id, $scope.MostViewProductData);
	}
	/*var findProductbyID = function(idProduct){
		for(int i = 0; i < $scope.ProductData.length; i++){
			if($scope.ProductData[i].idProduct == idProduct)
				return $scope.ProductData[i];
		}
	}
	this.redirectToProductPage = function(idProduct){
		var data = findProductbyID(idProduct);
		ShareDataService.set(data);
		window.location.href = "Product";
	}*/
	this.getnewProduct();
	this.getMostViewProduct();
});