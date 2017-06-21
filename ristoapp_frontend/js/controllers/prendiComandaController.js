myApp.controller("PrendiComandaController", function($scope, ajaxService, PrendiComandaService, $http, Pubnub) {



    var updateListaProdotti = function () {
        //ajaxService.getResource("http://localhost:8080/creaPietanza/getProdotti", null).then(
        $http.get("jsonFiles/menuAttuale.json").then(
            function (response) {
                //$scope.prodotti = CreaPietanzaService.parseProductList(response);
                var data = response.data;
                $scope.menuAttuale = data.menuAttuale;
            }
            , function (response) {
                alert("Couldn't get products");
            });
    };

    updateListaProdotti();


    $scope.selectedProd = [];
    $scope.associatedTags = [];
    $scope.nomePietanza = "";
    $scope.nomeNewTag = "";

    var searchIndex = function(searchTerm){
        for(var i = 0, len = $scope.selectedProd.length; i < len; i++) {
            if ($scope.selectedProd[i].name === searchTerm) {
                return i;
            }
        }
    };

    $scope.updateSelectedProd = function(nomeProd){
        var checkBox = document.getElementById("check.".concat(nomeProd));
        if(checkBox.checked) {
            var ingrediente = {nome:nomeProd,       quantita:1};
            $scope.selectedProd.push(ingrediente);
            $scope.selectedProd.sort(function(a, b){
                return (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0)
            });
        }else{
            var i = searchIndex(nomeProd);
            $scope.selectedProd.splice(i,1);
        }
    };



    /*
    $scope.inviaComanda = function(){
        var jsonComanda = angular.toJson($scope.selectedProd);
        ajaxService.sendResource("http://localhost:8080/comanda", jsonComanda).then(function (response) {
            console.log("successo : " + jsonComanda);
        }, function (response) {
            console.log("fallimento : " + jsonComanda);
            console.log(response)
        });
    };*/


    // Inizializzazione delle credenziali per PubNub

    Pubnub.init({
        publishKey: 'pub-c-18cb2897-1549-41e9-9011-c0c17480a1e4',
        subscribeKey: 'sub-c-23206a28-56ae-11e7-97fe-02ee2ddab7fe'
    });

    // Manda la comanda
    $scope.publish = function () {
        var jsonComanda = angular.toJson($scope.selectedProd);
        Pubnub.publish({
            channel: 'channel_comande',
            message: jsonComanda
        }, function (status, response){
            console.log(response);
            alert("COMANDA INVIATA");
        });
    };


    $scope.updateListaProdottiForChef = function () {
        //ajaxService.getResource("http://localhost:8080/creaPietanza/getProdotti", null).then(
/*
        $http.get("jsonFiles/menuAttuale.json").then(
*/
        ajaxService.getResource("http://localhost:8080/comanda", null).then(
            function (response) {

                var parsed = JSON.parse((angular.toJson(response)));
                for(var i in parsed){
                    console.log(parsed[i]);
                    $scope.currentItems.push(parsed[i]);
                }

                $scope.selectedProdForChef = $scope.currentItems;
            }
            , function (response) {
                alert("Couldn't get products");
            });
    };

});