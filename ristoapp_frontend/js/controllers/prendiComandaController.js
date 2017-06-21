myApp.controller("prendiComandaController", function($scope, ajaxService, PrendiComandaService, $http) {

    /*var stompClient = null;*/
    $scope.currentItems = [];


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



/*    $scope.inviaComanda = function(){
        var jsonComanda = angular.toJson($scope.selectedProd);
        ajaxService.sendResource("http://localhost:8080/comanda", jsonComanda).then(function (response) {
            console.log("successo : " + jsonComanda);
        }, function (response) {
            console.log("fallimento : " + jsonComanda);
            console.log(response)
        });
    };*/

    $scope.sendComanda = function () {
        var socket = new SockJS('http://localhost:8080/websocket');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            /*setConnected(true);*/
            console.log('Connected: ' + frame);
            var jsonComanda = angular.toJson($scope.selectedProd);
            console.log(stompClient);
            stompClient.send("http://localhost:8080/app/nuoveComande", {}, jsonComanda);
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



    $scope.connect = function() {
        var stompClient = null;
        var socket = new SockJS('http://localhost:8080/websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            /*setConnected(true);*/
            console.log('Connected: ' + frame);
            stompClient.subscribe('http://localhost:8080/topic/comande', function (comande) {
                console.log("arivato qualcosa");
                var parsed = JSON.parse((angular.toJson(comande)));
                for(var i in parsed){
                    console.log(parsed[i]);
                    $scope.currentItems.push(parsed[i]);
                }

                $scope.selectedProdForChef = $scope.currentItems;
/*                showComande(comanda.body);*/
            });
        });
    };

});