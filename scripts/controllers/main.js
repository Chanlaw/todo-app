
'use strict';
angular.module('appApp').controller('MainCtrl', ['$scope','readTasks', '$routeParams', function($scope, readTasks) {
  readTasks.success(function(data) {
  	$scope.tasks = data.records;
   });
}]);
