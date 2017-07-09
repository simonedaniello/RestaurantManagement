myApp.controller("CassaController", function($scope, CassaService, ajaxService) {

    $scope.numeroTavolo = null;
    $scope.comandaItems = [];
    $scope.prezzoTotale = 0;

    $scope.selectComanda = function () {
        if ($scope.numeroTavolo == null) {
            alert("Inserire il numero del tavolo!");
            return;
        }
        CassaService.getComanda("https://localhost:8080/comanda/tavolo/" + $scope.numeroTavolo).then(function (response) {
            $scope.comandaItems = response.data;
            $scope.prezzoTotale = CassaService.calcolaTotale($scope.comandaItems);
        }, function (error) {
            alert("Errore nel recuparare il conto");
        });
    };

    $scope.chiudiTavolo = function () {
        if ($scope.prezzoTotale == 0)
            return;

        for (var j=0;  j<$scope.comandaItems.length; j++) {
            console.log($scope.comandaItems[j]);
            var pietanzaResocontoDto = {value: $scope.comandaItems[j].quantita,
                prodottoId: $scope.comandaItems[j].id};
            var json = JSON.stringify(pietanzaResocontoDto);
            console.log(pietanzaResocontoDto);
            ajaxService.sendResource("https://localhost:8080/resoconto/venduto", json).then(
                function (response) {
                }
                ,function (response) {
                    alert("Couldn't send report");
                });
        }

        CassaService.updateComanda("https://localhost:8080/comanda/updateComanda/" + $scope.numeroTavolo).then(function (response) {
            if (response.data === true) {
                alert("Conto chiuso correttamente!");
                $scope.comandaItems = [];
                $scope.prezzoTotale = 0;
            }
        }, function (error) {
            alert("Errore chiusura del conto");
        });
    };
});

