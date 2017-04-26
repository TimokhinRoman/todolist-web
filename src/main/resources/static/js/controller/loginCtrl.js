todoApp
    .controller('loginCtrl', function ($rootScope, $scope, $http, $location) {
        $scope.login = function () {
            var config = {
                params: {
                    username: $scope.username,
                    password: $scope.password
                }
            };

            $http.post('/login', {}, config).then(function (response) {
                $location.path('/');
                $rootScope.user = response.data;
            }, function () {
                $scope.error = true;
            })
        };
    });