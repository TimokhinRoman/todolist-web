todoApp
    .controller('registerCtrl', function ($rootScope, $scope, $http, $location) {
        $scope.register = function () {
            var user = {
                name: $scope.username,
                password: $scope.password,
                confirmPassword: $scope.confirmPassword
            };

            $http.post('/register', user).then(function (response) {
                $location.path('/');
                $rootScope.user = response.data;
            }, function (response) {
                $scope.error = response.data;
            })
        };
    });