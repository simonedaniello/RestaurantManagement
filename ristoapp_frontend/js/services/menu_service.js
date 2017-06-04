
var myApp = angular.module("ristoApp");

myApp.service('MenuService', ["$http", function($http) {

    this.getMenu = function(url) {
        return $http.get(url);
    };
}]);