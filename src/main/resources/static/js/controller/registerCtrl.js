todoApp
    .controller('registerCtrl', function ($http) {
        var registerCtrl = this;

        registerCtrl.register = function () {
            var user = {
                name: registerCtrl.username,
                password: registerCtrl.password,
                passwordConfirm: registerCtrl.passwordConfirm
            };

            //$http.post('/register', user);
        };
    });