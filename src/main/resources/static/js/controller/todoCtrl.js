todoApp
    .controller('todoCtrl', function ($scope, $http, $filter, $routeParams) {
        var todoCtrl = this;

        todoCtrl.urlPrefix = '';

        function getTodosRequest() {
            return $http.get(todoCtrl.urlPrefix + '/todo');
        }

        function saveTodoRequest(todo) {
            return $http.post(todoCtrl.urlPrefix + '/todo/save', todo);
        }

        function removeTodoRequest(todo) {
            var config = {
                params: {
                    id: todo.id
                }
            };
            return $http.post(todoCtrl.urlPrefix + '/todo/remove', {}, config);
        }

        function reset() {
            $scope.editing = false;
            $scope.editedTodo = null;
            $scope.originalTodo = null;
        }

        $scope.todos = [];

        $scope.$watch('todos', function () {
            $scope.remainingCount = $filter('filter')($scope.todos, {completed: false}).length;
            $scope.completedCount = $scope.todos.length - $scope.remainingCount;
        }, true);

        $scope.$on('$routeChangeSuccess', function () {
            var status = $scope.status = $routeParams.status || '';
            $scope.statusFilter = (status === 'active') ?
                {completed: false} : (status === 'completed') ?
                    {completed: true} : {};

            var username = $scope.username = $routeParams.username;
            todoCtrl.urlPrefix = username ? 'users/' + username : '';

            getTodosRequest().then(function (response) {
                $scope.todos = response.data;
            });
        });

        $scope.addTodo = function () {
            var newTodo = {
                text: $scope.todoText.trim(),
                completed: false
            };

            if (!newTodo.text) {
                return;
            }

            saveTodoRequest(newTodo).then(function (response) {
                $scope.todos.push(response.data);
                $scope.todoText = '';
            });
        };

        $scope.removeTodo = function (todo) {
            removeTodoRequest(todo).then(function () {
                var i = $scope.todos.indexOf(todo);
                $scope.todos.splice(i, 1);
            })
        };

        $scope.toggleCompleted = function (todo) {
            saveTodoRequest(todo).then(null, function () {
                todo.completed = !todo.completed;
            });
        };

        $scope.editTodo = function (todo) {
            $scope.editing = true;
            $scope.editedTodo = todo;
            $scope.originalTodo = angular.extend({}, todo);
        };

        $scope.saveEdits = function (todo) {
            if (!$scope.editing) {
                return;
            }

            todo.text = todo.text.trim();

            if (todo.text === $scope.originalTodo.text) {
                reset();
                return;
            }

            (todo.text ? saveTodoRequest(todo) : $scope.removeTodo(todo)).then(null, function () {
                todo.text = $scope.originalTodo.text;
            }).finally(reset);
        };

        $scope.revertEdits = function (todo) {
            $scope.todos[$scope.todos.indexOf(todo)] = $scope.originalTodo;
            reset();
        };

        $scope.clearCompletedTodos = function () {
            var completedTodos = [];
            $scope.todos.forEach(function (todo) {
                if (todo.completed) {
                    completedTodos.push(todo);
                }
            });

            completedTodos.forEach(function (todo) {
                $scope.removeTodo(todo);
            });
        };
    });