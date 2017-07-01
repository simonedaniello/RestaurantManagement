myApp.controller("CassaController", function($scope, CassaService) {

    $scope.numeroTavolo = null;
    $scope.comandaItems = [];
    $scope.prezzoTotale = 0;

    $scope.selectComanda = function () {
        if ($scope.numeroTavolo == null) {
            alert("Inserire il numero del tavolo!");
            return;
        }
        CassaService.getComanda("http://localhost:8080/comanda/tavolo/" + $scope.numeroTavolo).then(function (response) {
            $scope.comandaItems = response.data;
            $scope.prezzoTotale = CassaService.calcolaTotale($scope.comandaItems);
        }, function (error) {
            alert("Errore nel recuparare il conto");
        });
    };

    $scope.chiudiTavolo = function () {
        if ($scope.prezzoTotale == 0)
            return;
        CassaService.updateComanda("http://localhost:8080/comanda/updateComanda/" + $scope.numeroTavolo).then(function (response) {
            if (response.data == true) {
                alert("Conto chiuso correttamente!");
                $scope.comandaItems = [];
                $scope.prezzoTotale = 0;
            }
        }, function (error) {
            alert("Errore chiusura del conto");
        });
    };
});

