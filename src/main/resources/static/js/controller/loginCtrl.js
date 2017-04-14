angular.module('todoApp')
    .controller('loginCtrl', function ($http) {
        var loginCtrl = this;

        loginCtrl.login = function () {
            var user = {
                name: loginCtrl.username,
                password: loginCtrl.password
            };

            //$http.post('/request/login', user);
        };
    });