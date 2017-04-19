todoApp
    .controller('loginCtrl', function ($scope, $http, $location) {
        $scope.login = function () {
            var config = {
                params: {
                    username: $scope.username,
                    password: $scope.password
                    //rememberme: rememberMe
                }
            };

            $http.post('/login', {}, config).then(function (response) {
                //console.log('success: ' + JSON.stringify(response));
                $location.path('/');
            }, function (response) {
                //console.log('error: ' + JSON.stringify(response));
                $scope.error = true;
            })
        };
    });