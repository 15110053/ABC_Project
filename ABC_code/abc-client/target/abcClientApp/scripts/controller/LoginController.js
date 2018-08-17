angular.module("EcommerceModule").controller("LoginController", ["$scope", "$mdDialog","$cookies", "$window", "LoginService", function($scope, $mdDialog, $cookies, $window, LoginService){
	$scope.userName;
	$scope.password;
	var errorDialog = function(ev){
		$mdDialog.show(
			$mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Notification')
				.textContent('Login failed')
				.ariaLabel('Alert Dialog Demo')
				.ok('Got it')
				.targetEvent(ev)
		);
	}
	this.login = function(ev){
		var data = {
			loginName: $scope.userName,
			password: $scope.password
		};
		LoginService.login(data).then(function(response){
			console.log(response);
			if(response.data.status == 0)
			{
				$cookies.putObject("token", response.data.object);
				$window.location.href= "/abcClientApp/";
			}else errorDialog(ev);
		}, function(error){
			console.log(error);
			$mdDialog.show(
				$mdDialog.alert()
					.clickOutsideToClose(true)
					.title('Notification')
					.textContent('Login failed')
					.ariaLabel('Alert Dialog Demo')
					.ok('Got it')
					.targetEvent(ev)
			);
		})
	}
	
	this.show = function(ev){
		$mdDialog.show(
		  $mdDialog.alert()
			.clickOutsideToClose(true)
			.title('This is an alert title')
			.textContent('You can specify some description text in here.')
			.ariaLabel('Alert Dialog Demo')
			.ok('Got it!')
			.targetEvent(ev)
		);
	}
}]);