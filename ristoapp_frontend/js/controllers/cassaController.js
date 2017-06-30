myApp.controller("cassaController", function($scope, cassaService) {

    $scope.prezzoFinale = 0;
    $scope.pietanze = [];

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

