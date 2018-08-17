angular.module("EcommerceModule").controller("ProfileManagementController", function ($scope, $mdDialog, ProfileManagementService){
	$scope.userInfo;
	var getUser = function(){
		ProfileManagementService.getUserInfo().then(
			function(response){
				$scope.userInfo = response.data.object;
				$scope.userInfo.identityNumber = parseInt($scope.userInfo.identityNumber);
				$scope.userInfo.phoneNumber = parseInt($scope.userInfo.phoneNumber);
			}, function(error){
				console.log(error);
			}
		)
	}
	this.updateUserInfo = function(){
		var data = {
			userId: $scope.userInfo.userId,
			userName: $scope.userInfo.userName,
			identityNumber: $scope.userInfo.identityNumber,
			phoneNumber: $scope.userInfo.phoneNumber,
			email: $scope.userInfo.email
		}
		ProfileManagementService.updateUserInfo(data).then(
			function(response){
				if(response.data.status == 0){
					$mdDialog.show(
						$mdDialog.alert()
							.clickOutsideToClose(true)
							.title('Notification')
							.textContent('change profile success')
							.ariaLabel('Alert Dialog Demo')
							.ok('Got it')
					)
				}else {
					$mdDialog.show(
						$mdDialog.alert()
							.clickOutsideToClose(true)
							.title('Notification')
							.textContent('change profile fail')
							.ariaLabel('Alert Dialog Demo')
							.ok('Got it')
					)
				}
			}, function(error){
				$mdDialog.show(
					$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Notification')
						.textContent('change profile fail')
						.ariaLabel('Alert Dialog Demo')
						.ok('Got it')
				)
			}
		)
	}
	getUser();
});
