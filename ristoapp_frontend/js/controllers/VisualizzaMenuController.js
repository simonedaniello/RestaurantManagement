
// Controller per la visualizzazione del menu e salvataggio come pdf.

myApp.controller("VisualizzaMenuController", ["$scope", "MenuService", "ajaxService", "$routeParams", function ($scope, MenuService, ajaxService, $routeParams) {

    $scope.menu = {nomeMenu: null, immagineMenu: null, categorie: null};

    // Nome del menù con cui fare la get al backend
    var nomeMenu = $routeParams.nomeMenu;

    $scope.visualizza = function () {
        // "jsonFiles/menu_mock.json"
        ajaxService.getResource("http://localhost:8080/menu/" + nomeMenu, null).then(function (response) {
            $scope.menu.nomeMenu = response.nomeMenu;
            $scope.menu.immagineMenu = response.immagineMenu;
            $scope.menu.categorie = response.categorie;
        }, function (error) {
            alert.log(error);
        });
    };

    $scope.visualizza();

    $scope.pdf = function () {
        var id = document.getElementById("toPdf");
        MenuService.getPdf(id, $scope.menu.nomeMenu);
    };

}]);
