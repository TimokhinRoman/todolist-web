angular.module('todoApp', ['ngRoute'])
    .config(function ($routeProvider) {

        $routeProvider
            .when('/', {
                templateUrl: 'todo.html',
                controller: 'todoCtrl'
            })
            .when('/todo', {
                templateUrl: 'todo.html',
                controller: 'todoCtrl'
            })
            .when('/login', {
                templateUrl: 'loginn.html',
                controller: 'loginCtrl',
                controllerAs: 'loginCtrl'
            })
            .when('/register', {
                templateUrl: 'register.html',
                controller: 'registerCtrl',
                controllerAs: 'registerCtrl'
            })
    });