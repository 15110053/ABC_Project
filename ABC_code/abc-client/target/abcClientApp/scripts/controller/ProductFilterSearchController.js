angular.module("EcommerceModule").controller("ProductFilterSearchController", function ($scope, $stateParams, ProductFilterpageService, CartService){
	var routeparamkeyWord = $stateParams.keyWord;
	this.keyWord = $stateParams.keyWord;
	$scope.pageTitle = "Search: "+ routeparamkeyWord;
	$scope.ProductData;
	$scope.ProductCount = 0;
	$scope.pageIndex = 1;
	var maxProductResult = 3;
	this.whiteframe = 3;
	this.hover = function(ev){
		var ele = angular.element(ev.currentTarget);
		ele.addClass("md-whiteframe-10dp");
	}
	this.leave = function(ev){
		var ele = angular.element(ev.currentTarget);
		ele.removeClass("md-whiteframe-10dp");
	}
	this.range = function(){
		var input = [];
		for(var i = 0; i < $scope.ProductCount/maxProductResult; i++){
			input.push(i+1);
		}
		return input;
	}
	this.openPageIndex = function(index){
		$scope.pageIndex = index;
		this.handleGetProduct();
	}
	var getProductBySearch = function(){
		ProductFilterpageService.httpGetProductBySearch(routeparamkeyWord, $scope.pageIndex, maxProductResult).then(
			function(response){
				$scope.ProductData = response.data.object;
			},function(error){
				console.log(error);
			}
		)
	}
	var getProductBySearchCount = function(){
		ProductFilterpageService.httpGetProductBySearchCount(routeparamkeyWord).then(
			function(response){
				$scope.ProductCount = response.data.object;
			},function(error){
				console.log(error);
			}
		)
	}
	this.handleGetProduct = function(){
		getProductBySearchCount();
		getProductBySearch();
	}
	this.addProductToCart = function(id){
		CartService.addProductToCart(id, $scope.ProductData);
	}
	this.handleGetProduct();
});