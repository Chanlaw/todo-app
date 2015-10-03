app.factory('readTasks', ['$http', function($http) {
	return $http.get('https://s3.amazonaws.com/tasks15215/tasks.json') 
            .success(function(data) { 
              return data; 
            }) 
            .error(function(err) { 
              return err; 
            }); 
}]);