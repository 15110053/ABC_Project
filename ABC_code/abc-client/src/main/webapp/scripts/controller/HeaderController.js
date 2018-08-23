angular.module("EcommerceModule").controller("HeaderController", function ($scope, $mdDialog, $cookies, $state, $window, MainpageService, CartService){
	$scope.info;
	$scope.loginStatus = $cookies.getObject("token") === undefined ? false : true;
	$scope.keyWord;
	$scope.CategoryData = [];
	$scope.SubCategoryData = [];
	this.BASEURL = "http://localhost:8080/"
	this.doSearch = function(key){
		if(key.which === 13 )
			window.location.href = "#!/search/"+$scope.keyWord;
	}
	var getCategory = function (callback1, callback2){
		MainpageService.httpGetCategory().then(
		function(response){
			$scope.CategoryData = response.data.object;
			callback1(callback2);
		}, function(error){
			console.log(error);
		})
	}
	var getSubCategory = function (callback){
		MainpageService.httpGetSubCategory().then(
		function(response){
			$scope.SubCategoryData = response.data.object;
			callback();
		}, function(error){
			console.log(error);
		})
	}
	var solveCategory = function(){
		for(var j = 0; j<$scope.CategoryData.length; j++){
			$scope.CategoryData[j].subcategory = [];
		}
		var j = 0;
		for(var i = 0 ; i<$scope.SubCategoryData.length; i++){
			for(; j<$scope.CategoryData.length; j++){
				
				if($scope.SubCategoryData[i].categoryDTO.idCategory == $scope.CategoryData[j].idCategory){
					$scope.CategoryData[j].subcategory.push($scope.SubCategoryData[i]);
					break;
				}
			}
		}
	}
	this.logout = function(){
		MainpageService.logOut($cookies.getObject("token")[1]).then(
			function(response){
				$cookies.remove("token");
				$window.location.href= "/abcClientApp/";
			}, function(error){
				$cookies.remove("token");
				$window.location.href= "/abcClientApp/";
			}
		)
	}
	this.openManagerProductPage = function(){
		$state.go("producerManagement.managementProduct");
	}

	this.openCart = function(){
		$state.go("userManagement.cart");
	}
	var getInfo = function(){
		var object = $cookies.getObject("token");
		if(object !== undefined){
			$scope.info = object[0];
		}
	}
	this.checkProducerRole = function(){
		var object = $cookies.getObject("token");
		if(object!== undefined && object[0].role !== "PRODUCER"){
			return false;
		}
		return true;
	}
	getInfo();
	getCategory(getSubCategory, solveCategory);
});