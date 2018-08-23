angular.module("EcommerceModule").controller("RegisterController", function($scope, $mdDialog, $state, RegisterService){
	$scope.selectedIndex=0;
	$scope.step1Status= true;
	$scope.step2Status= false;
	$scope.step3Status= false;
	this.openTab = function(step){
		if(step == "3" && checkStepTwoFilled() == true){
			$scope.selectedIndex=2;
			$scope.step1Status= false;
			$scope.step2Status= false;
			$scope.step3Status= true;
		}else if(step == "2" && checkStepOneFilled() == true){
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
	$scope.userName;
	$scope.password;
	$scope.email;
	$scope.fullName;
	$scope.phoneNumber;
	$scope.identityNumber;
	$scope.role;
	var checkStepOneFilled = function(){
		if($scope.userName == null || $scope.password == null || $scope.email == null){
			return false;
		}
		else{
			return true;
		}
	}
	var checkStepTwoFilled = function(){
		if($scope.fullName == null || $scope.phoneNumber == null || 
				$scope.identityNumber == null)
			return false;
		return true;
	}
	var checkStepThreeFilled = function(){
		console.log($scope.role);
		if($scope.role == null || $scope.role == undefined)
			return false;
		return true;
	}
	this.register = function(ev){
		if(checkStepOneFilled == false || checkStepTwoFilled == false || checkStepThreeFilled== false){
			return;
		}
		else {
			var data = {
					userName: $scope.fullName,
					identityNumber: $scope.identityNumber,
					loginName: $scope.userName,
					password: $scope.password,
					phoneNumber: $scope.phoneNumber,
					email: $scope.email,
					role: $scope.role
				};
			RegisterService.register(data).then(function(response){
				console.log(data);
				if(response.data.status==0){
					console.log(response);
					$mdDialog.show(
						$mdDialog.alert()
							.clickOutsideToClose(true)
							.title('Notification')
							.textContent('Register success')
							.ok('Got it!')
							.targetEvent(ev)
					).then(function(){
						$state.go("login");
					});
				}else {
					$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Register failed')
						.textContent('Please check your information again')
						.ok('Got it')
						.targetEvent(ev)
					);
				}
			},function(error){
				console.log(error);
				$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Register failed')
						.textContent('Please check your information again')
						.ok('Got it')
						.targetEvent(ev)
				);
			})	
		}
	}
});