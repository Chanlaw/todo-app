'use strict';
var app = angular.module('todo', ["xeditable", "ngRoute", "ui.bootstrap", 'ngMdIcons']);

app.config(['$routeProvider', function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl: 'views/current.html'
        })
        .when('/past',{
            templateUrl: 'views/past.html'
        })
}]);

app.run(function(editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

app.controller('mainController', function($scope, $http) {
    var coeff = 1000*60*5; //nearest time interval to round to
    var date = new Date();  
    var rounded = new Date(Math.ceil(date.getTime() / coeff) * coeff)
    $scope.formData = {
        text : "",
        time : rounded
    };
    

    // when landing on the page, get all todos and show them
    $http.get('/api/todos')
        .success(function(data) {
            $scope.todos = data;
        })
        .error(function(data) {
            console.log('Error: ' + data);
        });

    // when submitting the add form, send the data to the node API
    $scope.createTodo = function() {
        $http.post('/api/todos', $scope.formData)
            .success(function(data) {
                //reset the form so user can enter another task
                $scope.formData.text = "",
                $scope.todos = data; 
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    // delete all todos
    $scope.deletePastTodos = function() {
        $http.delete('/api/delete-past')
            .success(function(data) {
                $scope.todos = data;
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };


    // delete a todo 
    $scope.deleteTodo = function(id) {
        $http.delete('/api/todos/' + id)
            .success(function(data) {
                $scope.todos = data;
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    //update a todo
    $scope.updateTodo = function(id, update){
        $http.put('/api/todos/' + id, update)
            .success(function(data) {
                $scope.todos = data;
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    //complete a todo
    $scope.completeTodo = function(id){
        var time = new Date();
        var update = {completed: true,
            time_completed: time.toJSON()};
        $http.put('/api/todos/' + id, update)
            .success(function(data) {
                $scope.todos = data;
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    //uncomplete a todo
    $scope.reviveTodo = function(id){
        var update = {completed: false,
            time_completed: ""};
        $http.put('/api/todos/' + id, update)
            .success(function(data) {
                $scope.todos = data;
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };
});