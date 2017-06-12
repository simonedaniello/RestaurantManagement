/**
 * Created by Antonio on 07/06/2017.
 */

// Controller per la ricerca dei menu sulla base di filtri di ricerca e visualizzazione dell'elenco dei menu.

myApp.controller("RicercaMenuController", ["$scope", "MenuService", function ($scope, MenuService) {

    $scope.showList = false;

    $scope.ricerca = function () {
        MenuService.getMenu("jsonFiles/menu_list_mock.json").then(function (response) {
            var data = response.data;
            $scope.listaMenu = data;
        }, function (error) {
            console.log(error);
        });
        $scope.showList = true;
    };

}]);