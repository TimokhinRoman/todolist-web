var todoApp = angular.module('todoApp', ['ngRoute']);

todoApp
    .config(function ($routeProvider, $httpProvider) {
        var todoConfig = {
            templateUrl: 'html/todo.html',
            controller: 'todoCtrl'
        };

        $routeProvider
            .when('/', {
                redirectTo: '/todo'
            })
            .when('/todo', todoConfig)
            .when('/todo/:status', todoConfig)
            .when('/login', {
                templateUrl: 'html/login.html',
                controller: 'loginCtrl'
            })
            .when('/register', {
                templateUrl: 'html/register.html',
                controller: 'registerCtrl'
            })
            .when('/users', {
                templateUrl: 'html/users.html',
                controller: 'adminCtrl'
            })
            .when('/users/:username', {
                redirectTo: function (routeParams) {
                    return '/users/' + routeParams.username + '/todo';
                }
            })
            .when('/users/:username/todo', todoConfig)
            .when('/users/:username/todo/:status', todoConfig)
            .otherwise('/todo');

        $httpProvider.interceptors.push('authInterceptor');
    });