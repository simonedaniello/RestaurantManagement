/**
 * Created by Antonio on 07/06/2017.
 */

// Controller per la ricerca dei menu sulla base di filtri di ricerca e visualizzazione dell'elenco dei menu.

myApp.controller("RicercaMenuController", ["$scope", "MenuService", "$location", function ($scope, MenuService, $location) {

    $scope.showList = false;

    $scope.ricerca = function () {
        MenuService.getMenu("jsonFiles/menu_list_mock.json").then(function (response) {
            $scope.listaMenu = response.data;
        }, function (error) {
            console.log(error);
        });
        $scope.showList = true;
    };


    $scope.pickMenu = function (nomeMenu) {
        var requestedMenu = nomeMenu.toString().toLowerCase().replace(" ", "");
        $location.path("/ricercaMenu/" + requestedMenu);
    };

}]);