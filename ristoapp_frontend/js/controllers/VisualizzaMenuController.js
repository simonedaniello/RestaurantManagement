
// Controller per la visualizzazione del menu e salvataggio come pdf.

myApp.controller("VisualizzaMenuController", ["$scope", "MenuService", "$routeParams", function ($scope, MenuService, $routeParams) {

    $scope.menu = {nomeMenu: null, immagineMenu: null, categorie: null};

    // Nome del menù con cui fare la get al backend
    var nomeMenu = $routeParams.nomeMenu;

    $scope.visualizza = function () {
        // "jsonFiles/menu_mock.json"
        MenuService.getMenu("http://localhost:8080/menu/" + nomeMenu).then(function (response) {
            var data = response.data;
            console.log(response.data);
            $scope.menu.nomeMenu = data.nomeMenu;
            $scope.menu.immagineMenu = data.immagineMenu;
            $scope.menu.categorie = data.categorie;
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
