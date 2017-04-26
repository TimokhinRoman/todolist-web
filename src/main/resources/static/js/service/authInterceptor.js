todoApp
    .service('authInterceptor', function ($q, $location) {
        var service = this;
        service.responseError = function (response) {
            if (response.status === 401) {
                $location.path('/login');
            }
            return $q.reject(response);
        };
    });