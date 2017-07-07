
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
        .when("/modificaMenu/:nomeMenu", {
            templateUrl: "htmlFiles/modificaMenu.html",
            controller: "creaMenuCtrl"
        })
        .when("/creaPietanza", {
            templateUrl : "htmlFiles/creaPietanza.html",
            controller:"CreaPietanzaController"
        })
        .when("/gestisciEtichette", {
            templateUrl : "htmlFiles/gestisciTag.html",
            controller:"GestisciTagController"
        })
        .when("/prendiComanda", {
            templateUrl : "htmlFiles/prendiComanda.html",
            controller:"PrendiComandaController"
        })
        .when("/comandecuoco", {
            templateUrl : "htmlFiles/RisolviComandeCuoco.html",
            controller:"RisolviComandeController"
        })
        .when("/ordineMagCentr", {
            templateUrl : "htmlFiles/ordineMagazzinoCentrale.html",
            controller:"OrdineMagazzinoCentraleController"
        })
        .when("/cercaPietanza", {
            templateUrl: "htmlFiles/ricercaPietanze.html",
            css : "cssFiles/ricercaPietanzaTabs.css",
            controller: "ricercaPietanzaController"
        })
        .when("/modificaPietanza/:idPietanza", {
            templateUrl: "htmlFiles/modificaPietanza.html",
            controller: "CreaPietanzaController"
        })
        .when("/cassa", {
            templateUrl: "htmlFiles/cassa.html",
            controller: 'CassaController'
        })
        .otherwise({
            templateUrl : "htmlFiles/mainPage.html",
            controller: "mainPageController"
        });
});



myApp.controller('researchController', function($scope) {
    $scope.researchInText = function(tag, written) {
        return tag.contains(written);
    }
});


