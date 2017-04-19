todoApp
    .controller('registerCtrl', function ($scope, $http, $location) {
        $scope.register = function () {
            var user = {
                name: $scope.username,
                password: $scope.password,
                confirmPassword: $scope.confirmPassword
            };

            $http.post('/register', user).then(function (response) {
                $location.path('/');
            }, function (response) {
                //console.log(JSON.stringify(response));
                $scope.error = response.data;
            })
        };
    });