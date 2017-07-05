/**
 * Created by Antonio on 07/06/2017.
 */

// Controller per la ricerca dei menu sulla base di filtri di ricerca e visualizzazione dell'elenco dei menu.

myApp.controller("RicercaMenuController", ["$scope", "MenuService", "$location", function ($scope, MenuService, $location) {

    $scope.showList = false;

    /*
        Funzione principale di ricerca che viene richiamata per il findAll, ricerca per nome, per etichetta e per ingrediente.
        Params riguarda l'url a cui deve essere indirizzata la richiesta.
     */

    function search(params) {
        MenuService.getMenu("http://localhost:8080/menu" + params).then(function (response) {
            $scope.listaMenu = response.data;
        }, function (error) {
            alert("Errore nella richiesta");
            console.log(error);
        });
        $scope.showList = true;
    }

    $scope.findAll = function () {
        // "jsonFiles/menu_list_mock.json"
        search("/findAll");
    };

    $scope.search = {nome: "", ingrediente: "", etichetta: ""};

    $scope.ricercaNome = function () {
        var nome = $scope.search.nome;
        search("/nome/" + nome);
    };

    $scope.ricercaEtichetta = function () {
        var etichetta = $scope.search.etichetta;
        search("/etichetta/" + etichetta);
    };

    $scope.ricercaIngrediente = function () {
        var ingrediente = $scope.search.ingrediente;
        search("/ingrediente/" + ingrediente);
    };

    $scope.pickMenu = function (nomeMenu) {
        // Parsa la stringa del nome del menù togliendo gli spazi bianchi e facendone il lower case;
        //var requestedMenu = nomeMenu.toString().toLowerCase().replace(" ", "");
        $location.path("/ricercaMenu/" + nomeMenu);
    };

}]);