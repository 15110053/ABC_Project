angular.module("EcommerceModule").controller("ProductFilterController", function ($scope, $stateParams, ProductFilterpageService, CartService){
	var routeparam = $stateParams.filterparam;
	var routeparamCategoryId = $stateParams.categoryId;
	var routeparamsubCategoryName = $stateParams.subCategoryName;
	var routeparamCategoryName = $stateParams.categoryName;
	this.subcategoryID = $stateParams.categoryId;
	this.categoryName = $stateParams.categoryName;
	this.subCategoryName = $stateParams.subCategoryName;
	$scope.categoryId;
	$scope.pageTitle;
	$scope.ProductData;
	$scope.ProductCount = 0;
	$scope.pageIndex = 1;
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

	var getMostViewedProduct = function (){
		ProductFilterpageService.httpGetMostViewProduct($scope.pageIndex, maxProductResult).then(
		function(response){
			$scope.ProductData = response.data.object;
		}, function(error){
			console.log(error);
		})
	}
	var getNewProduct = function (){
		ProductFilterpageService.httpGetnewProduct($scope.pageIndex, maxProductResult).then(
		function(response){
			$scope.ProductData = response.data.object;
		}, function(error){
			console.log(error);
		})
	}
	var getProductByType = function(){
		ProductFilterpageService.httpGetProductById(routeparamCategoryId, $scope.pageIndex, maxProductResult).then(
			function(response){
				$scope.ProductData = response.data.object;
				$scope.categoryId = $scope.ProductData[0].subCategoryDTO.categoryDTO.idCategory;
			},function(error){
				console.log(error);
			}
		)
	}
	var getProductByCategory = function(){
		ProductFilterpageService.httpGetProductByCategory(routeparamCategoryId, $scope.pageIndex, maxProductResult).then(
			function(response){
				$scope.ProductData = response.data.object;
			},function(error){
				console.log(error);
			}
		)
	}
	var doNothing = function(){
		
	}
	this.handleGetProduct = function(){
		if(routeparamCategoryId != null){
			if(routeparamsubCategoryName != null){
				$scope.pageTitle = routeparamCategoryName + " " + routeparamsubCategoryName;
				getProductByType();
				getProductByTypeCount();
			}else {
				$scope.pageTitle = routeparamCategoryName;
				getProductByCategory();
				getProductByCategoryCount();
			}
			
		}else if(routeparam == "newproduct"){
			$scope.pageTitle = "New product";
			getNewProduct();
			getProductCount();
		}
		else if(routeparam == "mostviewproduct"){
			$scope.pageTitle = "The most viewed product";
			getMostViewedProduct();
			getProductCount();
		}
		else {
			$scope.pageTitle = "Not found product";
			doNothing();
		}
	}

	var getProductByTypeCount = function(){
		ProductFilterpageService.httpGetProductByTypeCount(routeparamCategoryId).then(
			function(response){
				$scope.ProductCount = response.data.object;
			}, function(error){
				console.log(error);
			}
		)
	}
	var getProductByCategoryCount = function(){
		ProductFilterpageService.httpGetProductByCategoryCount(routeparamCategoryId).then(
			function(response){
				$scope.ProductCount = response.data.object;
			}, function(error){
				console.log(error);
			}
		)
	}
	
	var getProductCount = function(){
		ProductFilterpageService.httpGetProductCount().then(
			function(response){
				$scope.ProductCount = response.data.object;
			}, function(error){
				console.log(error);
			}
		)
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
	this.addProductToCart = function(id){
		CartService.addProductToCart(id, $scope.ProductData);
	}
	this.handleGetProduct();
});