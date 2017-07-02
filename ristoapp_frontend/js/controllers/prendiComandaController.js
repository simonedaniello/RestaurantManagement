myApp.controller("PrendiComandaController", function($scope, ajaxService, PrendiComandaService, $http) {

    $scope.actuallyChecked = [];
    $scope.ricercaNome = "";

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
        console.log("sono nell'operazione");
        for(var i in $scope.selectedProd) {
            if ($scope.selectedProd[i].pietanza == searchTerm) {
                console.log("index = " + i);
                return i;
            }
        }
    };

    $scope.updateSelectedProd = function(nomeProd, prezzoProd){
        if($scope.numeroTavolo != null){
            var checkBox = document.getElementById("check.".concat(nomeProd));
            if(checkBox.checked) {
                $scope.actuallyChecked.push(checkBox);
                var ingrediente = {pietanza:nomeProd,       quantita:1,     prezzo:prezzoProd};
                $scope.selectedProd.push(ingrediente);
                $scope.selectedProd.sort(function(a, b){
                    return (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0)
                });
            }else{
                var i = searchIndex(nomeProd);
                $scope.selectedProd.splice(i,1);
            }
        }
        else {
            alert("Inserisci numero tavolo");
            var checkBox1 = document.getElementById("check.".concat(nomeProd));
            checkBox1.checked = false;
        }
    };


    $scope.numeroTavolo = null;
    var isTavoloSelected = false;

    var stompClient = null;

    $scope.publish = function () {

        if ($scope.numeroTavolo == null || isTavoloSelected == false) {
            alert("Verifica il numero del tavolo!");
            return;
        }


        var jsonFinale = {comandaItems: $scope.selectedProd, tavolo: $scope.numeroTavolo};
        var jsonComanda = angular.toJson(jsonFinale);
        if (stompClient != null) {

            stompClient.send("/app/comanda/publishComanda", {}, jsonComanda);
            alert("Comanda inviata correttamente!");

        } else {

            var socket = new SockJS('http://localhost:8080/websocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.send("/app/comanda/publishComanda", {}, jsonComanda);
                alert("Comanda inviata correttamente!");
            });

        }
        for(var k in $scope.actuallyChecked){
            $scope.actuallyChecked[k].checked = false;
        }
        $scope.actuallyChecked = [];
        $scope.selectedProd = [];
        isTavoloSelected = false;
    };




    $scope.confermaTavolo = function() {

        if ($scope.numeroTavolo == null) {
            alert("Verifica il numero del tavolo!");
            return;
        }

        // Controlla prima se il tavolo inserito per prendere la comanda è libero
        PrendiComandaService.getTavoli("http://localhost:8080/comanda/tavolo/findAllAttivi").then(function (response) {
            var tavoli = response.data;
            console.log(tavoli);
            if (isInArray($scope.numeroTavolo, tavoli)) { // E' presente!
                alert("Il tavolo selezionato è già occupato!");
                $scope.numeroTavolo = null;
                isTavoloSelected = false;
            }
            else {
                alert("Tavolo disponibile!");
                isTavoloSelected = true;
                for (var k in $scope.actuallyChecked) {
                    $scope.actuallyChecked[k].checked = false;
                }
                $scope.actuallyChecked = [];
                $scope.selectedProd = [];
            }
        }, function (error) {
            alert("Impossibile recuperare i tavoli occupati!");
        });

        function isInArray(value, array) {
            for (var i = 0; i < array.length; i++) {
                if (value == array[i])
                    return true;
            } return false;
        }
    }

});