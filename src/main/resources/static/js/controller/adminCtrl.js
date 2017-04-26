todoApp
    .controller('adminCtrl', function ($scope, $http) {
        $http.get('/users').then(function (response) {
            $scope.users = response.data;
        });
    });