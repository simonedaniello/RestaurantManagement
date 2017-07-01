myApp.controller("CassaController", function($scope, cassaService) {

    $scope.prezzoFinale = 0;
    $scope.comandaOrder = {comandaItems: [], tavolo: undefined};

    $scope.inviaRichiesta = function(numeroTavolo){

        var updateTagList = function() {
            cassaService.getTag().then(
                function (response) {
                    $scope.pietanze = cassaService.parseResponse(response);
                }
                , function (response) {
                    alert("C'è stato un problema con la richiesta");
                });
        };

        var pagamentoEffettuato = function() {
            cassaService.postConsumazioni();
        }
    }
});

