angular.module("EcommerceModule").factory("CartService", function($http, $cookies, $state){
	var returnValue = {};
	var BASEURL = "http://localhost:8080/";
	var findProduct = function(id, productList){
		for(var i=0; i< productList.length; i++){
			if(productList[i].idProduct == id){
				return productList[i];
			}
		}
	}
	returnValue.addProductToCart = function(id, productList){
		var product = findProduct(id, productList);
		if($cookies.getObject("token") === undefined || $cookies.getObject("token") === null){
			$state.go("login");
			return;
		}
		var productInCart = $cookies.getObject("cart");
		if(productInCart === undefined){
			productInCart = [];
		}
		for(var i = 0; i < productInCart.length; i++){
			if(productInCart[i].product.idProduct == product.idProduct){
				productInCart[i].quantity = productInCart[i].quantity + 1;
				$cookies.putObject("cart", productInCart);
				return;
			}
		}
		var productAddition = {
				product: product,
				quantity: 1
			}
		productInCart.push(productAddition);
		$cookies.putObject("cart", productInCart);
	}
	returnValue.changeValue = function(cart){
		$cookies.remove("cart");
		$cookies.putObject("cart", cart);
	}
	return returnValue;
});