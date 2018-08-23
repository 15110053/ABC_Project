angular.module("EcommerceModule").controller("EditProductController", function($scope, $cookies, $stateParams, $state, $mdDialog, ProductpageService, EditProductService, MainpageService){
	/*$scope.editData = {
		idProduct: "",
		description: "",
		image: "",
		price: "",
		productName: "",
		status: "",
		subCategoryDTO: {
			idSubCategory: ""
		}
	};*/
	var productId = $stateParams.id;
	$scope.categoryData = {};
	$scope.category;
	$scope.subCategory;
	$scope.editData= {};
	$scope.file;
	var CategoryData;
	var SubCategoryData;
//add or edit
	var run = function(){
		if(productId !== undefined)
			getProductData();
	}
//add or edit
//start get product data to edit
	var getProductData = function(){
		ProductpageService.httpGetProduct(productId).then(
			function(response){
				$scope.editData = response.data.object;
				$scope.category = $scope.editData.subCategoryDTO.categoryDTO.idCategory;
				$scope.subCategory = $scope.editData.subCategoryDTO.idSubCategory;
			},function(error){
				console.log(error);
			}
		)
	}
//end get product data to edit	
//start do edit (choose function to do)
	this.doEdit = function(){
		if($scope.editForm.$valid){
			if($scope.editData.idProduct === undefined || $scope.editData.idProduct === null){
				console.log("add product");
				addProduct();
			}else{
				console.log("edit product");
				editProduct();
			}
		}
	}
//end do edit (choose function to do)
//start add product area
	var addProduct = function(){
		$scope.editData.userDTO = {userName: $cookies.getObject("token")[1]};
		EditProductService.addProduct($scope.editData, $cookies.getObject("token")[1]).then(
			function(response){
				if(response.data.status == 1){
					$mdDialog.show(
						$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('add product failed')
						.ariaLabel('Alert Dialog')
						.ok('Got it')
					).then(function(){
						$state.go("main");
					});
				}else $state.go("producerManagement.managementProduct");
			}, function(error){
				console.log(error);
				$mdDialog.show(
					$mdDialog.alert()
					.clickOutsideToClose(true)
					.title('Notification')
					.textContent('add product failed')
					.ariaLabel('Alert Dialog')
					.ok('Got it')
				).then(function(){
					$state.go("main");
				});
			})
	}
//end add product area
//start edit product area
	var editProduct = function(){
		EditProductService.editProduct($scope.editData, $cookies.getObject("token")[1]).then(
			function(response){
				$state.go("producerManagement.managementProduct");
			}, function(error){
				$mdDialog.show(
					$mdDialog.alert()
					.clickOutsideToClose(true)
					.title('Notification')
					.textContent('edit product failed')
					.ariaLabel('Alert Dialog')
					.ok('Got it')
					.targetEvent(ev)
			);
			})
	}
	this.getSubCategory = function(){
		for(var i=0; i< $scope.categoryData.length; i++){
			if($scope.categoryData[i].idCategory == $scope.category)
				return $scope.categoryData[i].subcategory;
		}
	}
	var getCategory = function(callback1, callback2){
		MainpageService.httpGetCategory().then(
		function(response){
			CategoryData = response.data.object;
			callback1(callback2);
		}, function(error){
			console.log(error);
		})
	}
	var getSubCategory = function (callback){
		MainpageService.httpGetSubCategory().then(
		function(response){
			SubCategoryData = response.data.object;
			callback();
		}, function(error){
			console.log(error);
		})
	}
	var solveCategory = function(){
		for(var j = 0; j<CategoryData.length; j++){
			CategoryData[j].subcategory = [];
		}
		var j = 0;
		for(var i = 0; i<SubCategoryData.length; i++){
			for(; j<CategoryData.length; j++){
				
				if(SubCategoryData[i].categoryDTO.idCategory == CategoryData[j].idCategory){
					CategoryData[j].subcategory.push(SubCategoryData[i]);
					break;
				}
			}
		}
		$scope.categoryData = CategoryData;
	}

//end edit product area
//
	this.clearSubCategory = function(){
		if(!angular.equals($scope.editData, {}))
			$scope.editData.subCategoryDTO.idSubCategory = 0;
	}
	
	getCategory(getSubCategory, solveCategory);
	//getSubCategory(solveCategory);
	run();
	
});
