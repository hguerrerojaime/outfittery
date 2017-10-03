'use strict';

Object.prototype.equals = function(x)
{
    for(p in this)
    {
        switch(typeof(this[p]))
        {
            case 'object':
                if (!this[p].equals(x[p])) { return false }; break;
            case 'function':
                if (typeof(x[p])=='undefined' || (p != 'equals' && this[p].toString() != x[p].toString())) { return false; }; break;
            default:
                if (this[p] != x[p]) { return false; }
        }
    }

    for(p in x)
    {
        if(typeof(this[p])=='undefined') {return false;}
    }

    return true;
}

angular.module('gol',[])
.controller('AppController',['$scope','golService',function($scope,golService) {

  $scope.grid = {
     width: 5,
     height: 5,
     liveCells: []
  };



  $scope.callNextState = function() {
     golService.requestNextState($scope.grid).then(function(liveCells) {

       $scope.grid.liveCells = liveCells;



     });
  };

  $scope.checkCell = function(x,y) {
     var element = document.getElementById("check-"+x+"-"+y);

     if (element.checked) {

       var cell = { x:x,y:y };

       if (!$scope.containsLiveCell(cell)) {
         $scope.grid.liveCells.push(cell);
       }

     } else {
       var cell = { x:x,y:y };
       var cellIndex = $scope.grid.liveCells.indexOf(cell);

       if ($scope.containsLiveCell(cell)) {
         $scope.grid.liveCells.splice(cellIndex);
       }
     }

  };

  $scope.containsLiveCell = function(cell) {

    return $scope.grid.liveCells.some(function(e) { return e.x == cell.x && e.y == cell.y });
  }

}]).service('golService',['$http',function($http) {

  this.requestNextState = function(grid) {

      return $http.post('http://localhost:8080',grid).then(function(response) {
        return response.data;
      });
  };

}]).filter('range', function() {
  return function(input, total) {
    total = parseInt(total);
    for (var i=0; i<total; i++)
      input.push(i);
    return input;
  };
});
