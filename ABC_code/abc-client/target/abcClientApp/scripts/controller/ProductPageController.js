angular.module("EcommerceModule").controller("ProductPageController", function($scope, $stateParams, ProductpageService, CartService){
	var productId = $stateParams.id;
	var productName = $stateParams.name;
	$scope.productData;
	$scope.rate = 7;
	this.colors = ["red", "blue", "green", "grey"];
	this.StarupPage = "description";
	this.DescriptionStatus = true;
	this.RatingCommentStatus = false;
	this.OpenTab = function(page){
		if(page == "description"){
			this.DescriptionStatus = true;
			this.RatingCommentStatus = false;
		}else if(page == "ratingcomment"){
			this.DescriptionStatus = false;
			this.RatingCommentStatus = true;
		}else {
			this.DescriptionStatus = false;
			this.RatingStatus = false;
		}
			
	};
	this.getProductData = function(){
		ProductpageService.httpGetProduct(productId).then(
			function(response){
				$scope.productData = response.data.object;
			},function(error){
				console.log(error);
			}
		)
	}
	this.addProductToCart = function(id){
		var listProduct = [];
		listProduct.push($scope.productData);
		CartService.addProductToCart(id, listProduct);
	}
	this.getProductData();
});