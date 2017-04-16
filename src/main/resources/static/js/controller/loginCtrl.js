todoApp
    .controller('loginCtrl', function ($http, $location) {
        var loginCtrl = this;

        loginCtrl.login = function () {
            var credentials = {
                username: loginCtrl.username,
                password: loginCtrl.password
            };

            var config = {
                params: {
                    username: loginCtrl.username,
                    password: loginCtrl.password
                    //rememberme: rememberMe
                }
                //ignoreAuthModule: 'ignoreAuthModule'
            };

            $http.post('login', credentials).then(function (response) {
                console.log('success: ' + JSON.stringify(response));
            }, function () {
                console.log('error');
            })
        };
    });