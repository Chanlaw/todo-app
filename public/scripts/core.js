// public/core.js
var app = angular.module('todo', ["xeditable", "ui.bootstrap", 'ngMdIcons']);

app.run(function(editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

app.controller('mainController', function($scope, $http) {
    var coeff = 1000*60*5;
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
                date = new Date();
                rounded = new Date(Math.ceil(date.getTime() / coeff) * coeff)
                $scope.formData = {
                    text : "",
                    time : rounded
                };
                $scope.todos = data; 
            })
            .error(function(data) {
                console.log('Error: ' + data);
            });
    };

    // delete a todo after checking it
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
});