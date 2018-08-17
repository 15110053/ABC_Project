var app = angular.module("EcommerceModule", ["ui.router", "ngMaterial", "ngMessages", "ngAnimate", "ngCookies"]);
app.config(function($urlRouterProvider, $stateProvider){
	$urlRouterProvider.otherwise('/404');
	$stateProvider
		.state("m", {url: "",
					templateUrl : "views/mainpage.html"})
		.state("main", {url: "/",
					templateUrl : "views/mainpage.html"})
		.state("productFilter", { url: "/product/:filterparam", 
								templateUrl : "views/productfilter.html", 
								controller: "ProductFilterController", 
								controllerAs: "ctrl"})
		.state("productWithSubCategory", 
			{ url: "/product/:categoryName/:subCategoryName/:categoryId", 
			templateUrl : "views/productfilter.html", 
			controller: "ProductFilterController", 
			controllerAs: "ctrl"})
		.state("productWithCategory", { url: "/product/:categoryName/:categoryId", 
										templateUrl : "views/productfilter.html", 
										controller: "ProductFilterController", 
										controllerAs: "ctrl"})
		.state("productSearch", { url: "/search/:keyWord", 
									templateUrl : "views/productfilter.html", 
									controller: "ProductFilterSearchController", 
									controllerAs: "ctrl"})
		.state("login", { url: "/login", 
						templateUrl : "views/loginpage.html", 
						controller: "LoginController", 
						controllerAs: "ctrl"})
		.state("register", { url: "/register", 
							templateUrl : "views/registerpage.html", 
							controller: "RegisterController", 
							controllerAs: "ctrl"})
		.state("productDetail", { url: "/productDetail/:id/:name", 
								templateUrl : "views/productpage.html"})
								
		//start consumer management
		.state("userManagement", { url: "/userManagement", 
						templateUrl : "views/cartpage.html", 
						controller: "UserManagementController", 
						controllerAs: "ctrl"})
		.state("userManagement.cart", { url: "/cart", 
						templateUrl : "views/cart.html", 
						controller: "CartController", 
						controllerAs: "ctrl"})
		.state("userManagement.orderList", { url: "/orderList", 
						templateUrl : "views/orderlist.html", 
						controller: "OrderListController", 
						controllerAs: "ctrl"})
		.state("userManagement.profileManagement", { url: "/profile", 
						templateUrl : "views/profilemanagementpage.html", 
						controller: "ProfileManagementController", 
						controllerAs: "ctrl"})
		//end consumer management
		//start producer management
		.state("producerManagement", { url: "/producerManagement", 
						templateUrl : "views/producermanagement.html", 
						controller: "ProducerManagementController", 
						controllerAs: "ctrl"})
		.state("producerManagement.managementProduct", { url: "/managementProduct",
						templateUrl : "views/managementproductpage.html", 
						controller: "ManagementProductController",
						controllerAs: "ctrl"})
		.state("producerManagement.orderList", { url: "/orderList", 
						templateUrl : "views/orderlist.html", 
						controller: "OrderListController", 
						controllerAs: "ctrl"})
		.state("producerManagement.profileManagement", { url: "/producerProfile", 
						templateUrl : "views/profilemanagementpage.html", 
						controller: "ProfileManagementController", 
						controllerAs: "ctrl"})
		//end producer management
		.state("managementProduct", { url: "/managementProduct", 
						templateUrl : "views/managementproductpage.html", 
						controller: "ManagementProductController",
						controllerAs: "ctrl"})
		.state("editProduct", { url: "/editProduct/:id", 
						templateUrl : "views/editproductpage.html", 
						controller: "EditProductController",
						controllerAs: "ctrl"})
		.state("addProduct", { url: "/editProduct",
						templateUrl : "views/editproductpage.html", 
						controller: "EditProductController",
						controllerAs: "ctrl"})
		.state("orderPage", { url: "/order",
						templateUrl : "views/orderpage.html", 
						controller: "OrderPageController",
						controllerAs: "ctrl"})
		
		.state("otherwise", { url: "/404", templateUrl : "views/404.html"});
	/*
	$routeProvider
	.when("/", {templateUrl : "views/mainpage.html"})
	.when("/product/:filterparam", {templateUrl : "views/productfilter.html", 
	controller: "ProductFilterController", controllerAs: "ctrl"})
	.when("/product/:categoryName/:subCategoryName/:categoryId", 
	{templateUrl : "views/productfilter.html", controller: "ProductFilterController", controllerAs: "ctrl"})
	.when("/product/:categoryName/:categoryId", 
	{templateUrl : "views/productfilter.html", controller: "ProductFilterController", controllerAs: "ctrl"})
	.when("/search/:keyWord", 
	{templateUrl : "views/productfilter.html", controller: "ProductFilterSearchController", controllerAs: "ctrl"})
	.when("/login", 
	{templateUrl : "views/loginpage.html", controller: "LoginController", controllerAs: "ctrl"})
	.when("/register", 
	{templateUrl : "views/registerpage.html", controller: "RegisterController", controllerAs: "ctrl"})
	.when("/productDetail/:id/:name", {templateUrl : "views/productpage.html"})
	.when("/cart", {templateUrl : "views/cartpage.html", controller: "CartController", controllerAs: "ctrl"})
	.otherwise({templateUrl: "views/404.html"});
	*/
});

app.run(["$state", "$rootScope", "$cookies", "$transitions", "$mdDialog", function($state, $rootScope, $cookies, $transitions, $mdDialog){
	/*$rootScope.$on("$stateChangeStart", function(e, toState, toParams, fromState, fromParams){
		
		console.log("stateChangeStart234");
	});*/
	
	/*$transitions.onBefore({}, function(transition) {
		if(transition.to().name === "cart" && ($cookies.get("token") === undefined || $cookies.get("token") === null))
			$state.go("login");
    });*/

	$transitions.onStart({}, function(transition){
		if(transition.to().name === "userManagement" || transition.to().name === "userManagement.cart" || transition.to().name === "userManagement.orderList"){
			var object = $cookies.getObject("token");
			if(object === undefined || object === null ){
				$state.go("login");
			}else if(object[0].role !== "CONSUMER"){
				$state.go("main");
				$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('not permission')
						.ariaLabel('Alert Dialog Demo')
						.ok('Got it')
				);
			}
		}
		if(transition.to().name === "orderPage"){
			var object = $cookies.getObject("token");
			if(object === undefined || object === null ){
				$state.go("login");
			}else if(object[0].role !== "CONSUMER"){
				$state.go("main");
				$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('not permission')
						.ariaLabel('Alert Dialog Demo')
						.ok('Got it')
				);
			}
		}
		if(transition.to().name === "login" && $cookies.get("token") !== undefined && $cookies.get("token") !== null)
			$state.go("main");
		if(transition.to().name === "managementProduct"){
			var object = $cookies.getObject("token");
			if(object === undefined || object === null || object[0].role !== "PRODUCER"){
				$state.go("main");
				$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('not permission')
						.ariaLabel('Alert Dialog Demo')
						.ok('Got it')
				);
			}
		}
		if(transition.to().name === "editProduct"){
			var object = $cookies.getObject("token");
			if(object === undefined || object === null || object[0].role !== "PRODUCER"){
				$state.go("main");
				$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('not permission')
						.ariaLabel('Alert Dialog Demo')
						.ok('Got it')
				);
			}
		}
		if(transition.to().name === "addProduct"){
			var object = $cookies.getObject("token");
			if(object === undefined || object === null || object[0].role !== "PRODUCER"){
				$state.go("main");
				$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('not permission')
						.ariaLabel('Alert Dialog Demo')
						.ok('Got it')
				);
			}
		}
	});
	/*$transitions.onSuccess({}, function() {
		console.log("statechange success");
		alert("onSuccess");
	});*/
	
}]);