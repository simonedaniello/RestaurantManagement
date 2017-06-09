/**
 * Created by dandi on 13/05/17.
 */

var myApp = angular.module("ristoApp", ["ngRoute", "ngStorage"]);


myApp.config(function($routeProvider) {
    $routeProvider
        .when("/creaCategoria", {
            templateUrl : "htmlFiles/tempCategoria.html",
            controller: "creaCategoriaController"
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
            controller:"creaMenuCtrl"
        })
        .when("/creaPietanza", {
            templateUrl : "htmlFiles/creaPietanza.html",
            controller:"CreaPietanzaController"
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

