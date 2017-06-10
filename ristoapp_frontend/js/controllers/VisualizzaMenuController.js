
// Controller per la visualizzazione del menu e salvataggio come pdf.

myApp.controller("VisualizzaMenuController", ["$scope", "MenuService", "$routeParams", function ($scope, MenuService, $routeParams) {

    $scope.menu = {nomeMenu: undefined, immagineMenu: undefined, categorie: undefined};

    // Nome del menù con cui fare la get al backend
    var nomeMenu = $routeParams.nomeMenu;

    $scope.visualizza = function () {
        MenuService.getMenu("jsonFiles/menu_mock.json").then(function (response) {
            var data = response.data;
            $scope.menu.nomeMenu = data.nomeMenu;
            $scope.menu.immagineMenu = data.immagineMenu;
            $scope.menu.categorie = data.categorie;
        }, function (error) {
            console.log(error);
        });
    };

    $scope.visualizza();

    $scope.pdf = function () {
        var id = document.getElementById("toPdf");
        MenuService.getPdf(id, $scope.menu.nomeMenu);
    };


    /*
     ajaxService.getResource("../jsonFiles/menu_mock.json", nomeMenu).then(function (response) {
     var data = response.data;
     $scope.menu.nomeMenu = data.nomeMenu;
     $scope.menu.categorie = data.categorie;
     }, function (error) {
     console.log(error);
     });
     */
}]);
