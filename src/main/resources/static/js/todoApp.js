var todoApp = angular.module('todoApp', ['ngRoute']);

todoApp
    .service('authInterceptor', function ($q, $location) {
        var service = this;

        service.responseError = function (response) {
            if (response.status === 401) {
                $location.path('/login');
                //window.location = "/login";
            }
            return $q.reject(response);
        };
    })
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                redirectTo: '/todo'
            })
            .when('/todo', {
                templateUrl: 'html/todo.html',
                controller: 'todoCtrl'
            })
            .when('/todo/:status', {
                templateUrl: 'html/todo.html',
                controller: 'todoCtrl'
            })
            .when('/login', {
                templateUrl: 'html/login.html',
                controller: 'loginCtrl'
                //controllerAs: 'loginCtrl'
            })
            .when('/register', {
                templateUrl: 'html/register.html',
                controller: 'registerCtrl'
                //controllerAs: 'registerCtrl'
            })
            .otherwise('/todo');

        $httpProvider.interceptors.push('authInterceptor');

        //$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });