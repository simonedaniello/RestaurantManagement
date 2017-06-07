/**
 * Created by dandi on 13/05/17.
 */

var myApp = angular.module("ristoApp", ["ngRoute"]);


myApp.config(function($routeProvider) {
    $routeProvider
        .when("/monday", {
            templateUrl : "htmlFiles/tempCategoria.html",
            controller: "creaCategoriaCtrl"
        })
        .when("/table", {
            templateUrl : "htmlFiles/ricercaMenu.html",
            controller:"RicercaMenuController"
        })
        .when("/table/:nomeMenu", {
            templateUrl : "htmlFiles/visualizzaMenu.html",
            css : "cssFiles/menu.css",
            controller : "MenuController"
        })
        .when("/creamenu", {
            templateUrl : "htmlFiles/creaMenu.html",
            controller:"creaCtrl"
        })
        .otherwise({
            templateUrl : "htmlFiles/mainPage.html"
        });
});



myApp.controller('researchController', function($scope) {
    $scope.researchInText = function(tag, written) {
        return tag.contains(written);
    }
});

/*c'è un problema con l'header. Vedi da qui giù. Non appena
* risolvi puoi cancellare tutto da qui giù in poi*/


myApp.controller("headerController", function ($scope) {

    $scope.loghide = true;
    $scope.reghide = true;

    $scope.registerClick = function () {
        $scope.loghide = true;
        $scope.reghide = !$scope.reghide;
    };

    $scope.loginClick = function () {
        $scope.reghide = true;
        $scope.loghide = !$scope.loghide;
    };

});
