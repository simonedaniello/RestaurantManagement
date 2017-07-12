/**
 * Created by Antonio on 07/06/2017.
 */

// Controller per la ricerca dei menu sulla base di filtri di ricerca e visualizzazione dell'elenco dei menu.

myApp.controller("RicercaMenuController", ["$scope", "$location", "ajaxService", function ($scope, $location, ajaxService) {

    $scope.showList = false;

    /*
        Funzione principale di ricerca che viene richiamata per il findAll, ricerca per nome, per etichetta e per ingrediente.
        Params riguarda l'url a cui deve essere indirizzata la richiesta.
     */

    function search(params) {
        $scope.par = params;
        ajaxService.getResource("https://localhost:8080/menu" + params, null).then(function (response) {
            $scope.listaMenu = response;
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

    $scope.findMenuAttivo = function(){
        search("/findMenuAttivo");
    };

    $scope.search = {nome: "", ingrediente: "", etichetta: ""};

    $scope.ricercaNome = function () {
        if ($scope.search.nome === "") {
            alert("Specificare nome del menu");
            return;
        }
        var nome = $scope.search.nome;
        search("/nome/" + nome);
    };

    $scope.ricercaEtichetta = function () {
        if ($scope.search.etichetta === "") {
            alert("Specificare il nome dell'etichetta");
            return;
        }
        var etichetta = $scope.search.etichetta;
        search("/etichetta/" + etichetta);
    };

    $scope.ricercaIngrediente = function () {
        if ($scope.search.ingrediente === "") {
            alert("Specificare il nome dell'ingrediente");
            return;
        }
        var ingrediente = $scope.search.ingrediente;
        search("/ingrediente/" + ingrediente);
    };

    $scope.pickMenu = function (nomeMenu) {
        // Parsa la stringa del nome del menù togliendo gli spazi bianchi e facendone il lower case;
        //var requestedMenu = nomeMenu.toString().toLowerCase().replace(" ", "");
        $location.path("/ricercaMenu/" + nomeMenu);
    };

    $scope.deleteMenu = function (nomeMenu) {
        ajaxService.deleteResource("https://localhost:8080/menu/" + nomeMenu, null).then(function (response) {
            var index = searchIndex(nomeMenu, $scope.listaMenu);
            $scope.listaMenu.splice(index, 1);
        }, function (error) {
            alert("Errore nell'eliminazione");
            console.log(error);
        });
    };

    $scope.modificaMenu = function (nomeMenu) {
        $location.path("modificaMenu/"+ nomeMenu.toString());
    };

    $scope.attivaMenu = function (nomeMenu) {
        ajaxService.updateResource("https://localhost:8080/menu/" + nomeMenu, null).then(function (response) {
            alert("Menu reso attivo con successo");
            search($scope.par);
        }, function (error) {
            alert("Errore nell'operazione");
            console.log(error);
        });
    };

    var searchIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i].nome === searchTerm) {
                return i;
            }
        }
    };

}]);