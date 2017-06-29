myApp.controller("OrdineMagazzinoCentraleController", function($scope, ajaxService, $http) {

    $scope.selectedProd = [];

    var updateListaProdotti = function () {
        $http.get("jsonFiles/prodotti.json").then(
            function (response) {
                var data = response.data;
                $scope.prodotti = data.prodotti;
            }
            , function (response) {
                alert("Couldn't get products");
            });
    };

    updateListaProdotti();


    var searchIndex = function(searchTerm){
        for(var i = 0, len = $scope.selectedProd.length; i < len; i++) {
            if ($scope.selectedProd[i].name === searchTerm) {
                return i;
            }
        }
    };

    $scope.updateSelectedProd = function(nomeProd, id){
        var checkBox = document.getElementById("check.".concat(nomeProd));
        if(checkBox.checked) {
            var prodottoOrdinare = {nomeProdotto:nomeProd, quantita:1, prodottoId: id};
            $scope.selectedProd.push(prodottoOrdinare);
        }else{
            var i = searchIndex(nomeProd);
            $scope.selectedProd.splice(i,1);
        }
    };

    $scope.sendOrder = function(){
        var dtoOrdine = {
            prodotti: $scope.selectedProd};
        var jsonOrdine = angular.toJson(dtoOrdine);
        ajaxService.sendResource("http://localhost:8080/order", jsonOrdine).then(function (response) {
        }, function (response) {
            alert("Couldn't send order.        Bisogna collegare l'app");
            console.log(response)
        });
    };

});