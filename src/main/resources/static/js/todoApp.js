var todoApp = angular.module('todoApp', ['ngRoute']);

todoApp
    .service('authInterceptor', function($q, $location) {
        var service = this;

        service.responseError = function(response) {
            if (response.status === 401){
                $location.path('/login');
                //window.location = "/login";
            }
            return $q.reject(response);
        };
    })
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'templates/todo.html',
                controller: 'todoCtrl'
            })
            .when('/todo', {
                templateUrl: 'todo.html',
                controller: 'todoCtrl'
            })
            .when('/login', {
                templateUrl: 'templates/login.html',
                controller: 'loginCtrl',
                controllerAs: 'loginCtrl'
            })
            .when('/register', {
                templateUrl: 'templates/register.html',
                controller: 'registerCtrl',
                controllerAs: 'registerCtrl'
            });

        $httpProvider.interceptors.push('authInterceptor');

        //$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });