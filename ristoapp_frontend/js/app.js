
var myApp = angular.module("ristoApp", ["ngRoute", "ngStorage"]);


myApp.config(function($routeProvider) {
    $routeProvider
        .when("/ricercaMenu", {
            templateUrl : "htmlFiles/ricercaMenu.html",
            controller:"RicercaMenuController",
            activetab: "menu"
        })
        .when("/ricercaMenu/:nomeMenu", {
            templateUrl : "htmlFiles/visualizzaMenu.html",
            css : "cssFiles/menu.css",
            controller : "VisualizzaMenuController",
            activetab: "menu"
        })
        .when("/creamenu", {
            templateUrl : "htmlFiles/creaMenu.html",
            controller:"creaMenuCtrl",
            activetab: "creamenu"
        })
        .when("/modificaMenu/:nomeMenu", {
            templateUrl: "htmlFiles/modificaMenu.html",
            controller: "creaMenuCtrl",
            activetab: "menu"
        })
        .when("/creaPietanza", {
            templateUrl : "htmlFiles/creaPietanza.html",
            controller:"CreaPietanzaController",
            activetab: "pietanze"
        })
        .when("/gestisciEtichette", {
            templateUrl : "htmlFiles/gestisciTag.html",
            controller:"GestisciTagController",
            activetab: "gestiscitag"
        })
        .when("/prendiComanda", {
            templateUrl : "htmlFiles/prendiComanda.html",
            controller:"PrendiComandaController",
            activetab: "prendicomanda"
        })
        .when("/comandecuoco", {
            templateUrl : "htmlFiles/RisolviComandeCuoco.html",
            controller:"RisolviComandeController",
            activetab: "listacomandecuoco"
        })
        .when("/ordineMagCentr", {
            templateUrl : "htmlFiles/ordineMagazzinoCentrale.html",
            controller:"OrdineMagazzinoCentraleController",
            activetab: "ordine"
        })
        .when("/cercaPietanza", {
            templateUrl: "htmlFiles/ricercaPietanze.html",
            css : "cssFiles/ricercaPietanzaTabs.css",
            controller: "ricercaPietanzaController",
            activetab: "pietanze"
        })
        .when("/modificaPietanza/:idPietanza", {
            templateUrl: "htmlFiles/modificaPietanza.html",
            controller: "CreaPietanzaController",
            activetab: "pietanze"
        })
        .when("/cassa", {
            templateUrl: "htmlFiles/cassa.html",
            controller: 'CassaController',
            activetab: "cassa"
        })
        .when("/gestionePersonale", {
            templateUrl: "htmlFiles/gestioneDipendenti.html",
            controller: 'GestioneDipendentiController'
        })
        .otherwise({
            templateUrl : "htmlFiles/mainPage.html",
            activetab: "home"
        });
});


myApp.controller('researchController', function($scope) {
    $scope.researchInText = function(tag, written) {
        return tag.contains(written);
    }
});


