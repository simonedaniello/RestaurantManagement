/**
 * Created by dandi on 13/05/17.
 */

var myApp = angular.module("ristoApp", ["ngRoute", "ngStorage", "pubnub.angular.service"]);


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
            controller:"PrendiComandaController"
        })
        .when("/comandecuoco", {
            templateUrl : "htmlFiles/risolviComandeCuoco.html",
            controller:"RisolviComandeController"
        })
        .when("/cercaPietanza", {
            templateUrl: "htmlFiles/ricercaPietanze.html",
            css : "cssFiles/ricercaPietanzaTabs.css",
            controller: "ricercaPietanzaController"
        })
        /*.when("/dovesiamo", {
            templateUrl: "htmlFiles/dovesiamo.html",
            controller: "doveSiamoController"
        })*/
        .when('/dovesiamo', {
            controller: 'doveSiamoController',
            templateUrl: "htmlFiles/dovesiamo.html",
            resolve: {
                init: function() {
                    return function() {
                        console.log('Loading Blog');
                    }
                }
            }
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

