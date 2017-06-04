
var myApp = angular.module("ristoApp");

myApp.controller("MenuController", ["$scope", "MenuService", function ($scope, MenuService) {

    $scope.menu = {nomeMenu: undefined, categorie: undefined};

    MenuService.getMenu("../jsonFiles/menu_mock.json").then(function (response) {
        var data = response.data;
        $scope.menu.nomeMenu = data.nomeMenu;
        $scope.menu.categorie = data.categorie;
    }, function (error) {
        console.log(error);
    });
}]);
