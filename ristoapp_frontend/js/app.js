/**
 * Created by dandi on 13/05/17.
 */

var myApp = angular.module("ristoApp", ["ngRoute", "ngStorage"]);


myApp.config(function($routeProvider) {
    $routeProvider
        .when("/ricercaMenu", {
            templateUrl : "htmlFiles/ricercaMenu.html",
            controller:"RicercaMenuController"
        })
        .when("/ricercaMenu/:nomeMenu", {
            templateUrl : "htmlFiles/visualizzaMenu.html",
            css : "cssFiles/menu.css",
            controller : "VisualizzaMenuController"
        })
        .when("/creamenu", {
            templateUrl : "htmlFiles/creaMenu.html",
            controller:"creaMenuCtrl"
        })
        .when("/creaPietanza", {
            templateUrl : "htmlFiles/creaPietanza.html",
            controller:"CreaPietanzaController"
        })
        .when("/prendiComanda", {
            templateUrl : "htmlFiles/prendiComanda.html",
            controller:"prendiComandaController"
        })
        .when("/comandecuoco", {
            templateUrl : "htmlFiles/RisolviComandeCuoco.html",
            controller:"prendiComandaController"
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

