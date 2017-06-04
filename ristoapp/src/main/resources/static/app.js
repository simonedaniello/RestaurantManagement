//'use strict';

// Declare app level module which depends on views, and components
/*angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'myApp.version'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/view1'});
}]);*/

var app = angular.module("myApp",['ngRoute']);

app.service("myAjax", function($q,$http) {

    var ajax = function(method,url,data) {

        var deferred = $q.defer();
        var request = {
            method: method,
            url: url,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        if (method === 'GET') {
            request.params = data;
        } else {
            request.data = data;
        }
        $http(request).then(function(response){
            deferred.resolve(response.data);
        },function(response, status){
            deferred.reject({data: response, status: status});
        });
        return deferred.promise;
    };

    this.get = function(params) {
        return ajax("GET", "dishes", params);
    };

    this.post = function(data) {
        return ajax("POST", "/someUrl", data);
    };

});


app.controller("myController",function($scope, myAjax) {

    var init = function () {
        $scope.records = [];
        $scope.selected = [];
        var param = null;
        myAjax.get(param).then(function (data) {
            $scope.records = data;
            $scope.records.sort();
        }, function (err) {
            console.log("Err: " + err);
        });
    };

    init();

    $scope.updateSelected = function (idPietanza) {
        var checkBox = document.getElementById(idPietanza);
        var name = idPietanza;
        if (checkBox.checked) {
            $scope.selected.push(name);
            $scope.selected.sort();
        } else {
            $scope.selected.splice($scope.selected.indexOf(name), 1);
        }
    };
});

