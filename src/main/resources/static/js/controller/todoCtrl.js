todoApp
    .controller('todoCtrl', function ($scope, $http, $filter, $route, $routeParams) {

        var todos = [];

        $http.get('/user/todo').then(function (response) {
            todos = $scope.todos = response.data;
        });

        var save = function (todo) {
            return $http.post('user/todo/save', todo);
        };

        var remove = function (todo) {
            var config = {
                params: {
                    id: todo.id
                }
            };
            return $http.get('user/todo/remove/', config).then(function () {
                var i = todos.indexOf(todo);
                todos.splice(i, 1);
            });
        };

        var reset = function () {
            $scope.editing = false;
            $scope.editedTodo = null;
            $scope.originalTodo = null;
        };

        $scope.$watch('todos', function () {
            $scope.remainingCount = $filter('filter')(todos, {completed: false}).length;
            $scope.completedCount = todos.length - $scope.remainingCount;
        }, true);

        $scope.$on('$routeChangeSuccess', function () {
            var status = $scope.status = $routeParams.status || '';
            console.log(status);
            $scope.statusFilter = (status === 'active') ?
                {completed: false} : (status === 'completed') ?
                    {completed: true} : {};
        });

        $scope.addTodo = function () {
            var newTodo = {
                text: $scope.todoText.trim(),
                completed: false
            };

            if (!newTodo.text) {
                return;
            }

            save(newTodo).then(function (response) {
                todos.push(response.data);
                $scope.todoText = '';
            });
        };

        $scope.removeTodo = remove;

        $scope.toggleCompleted = function (todo) {
            save(todo).then(null, function () {
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

            (todo.text ? save(todo) : remove(todo)).then(null, function () {
                todo.text = $scope.originalTodo.text;
            }).finally(reset);
        };

        $scope.revertEdits = function (todo) {
            todos[todos.indexOf(todo)] = $scope.originalTodo;
            reset();
        };

        $scope.clearCompletedTodos = function () {
            var completedTodos = [];
            todos.forEach(function (todo) {
                if (todo.completed) {
                    completedTodos.push(todo);
                }
            });

            completedTodos.forEach(function (todo) {
               remove(todo);
            });
        };
    });