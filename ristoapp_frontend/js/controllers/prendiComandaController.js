myApp.controller("PrendiComandaController", function($scope, ajaxService, PrendiComandaService, $http, Pubnub) {

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
        for(var i = 0, len = $scope.selectedProd.length; i < len; i++) {
            if ($scope.selectedProd[i].name === searchTerm) {
                return i;
            }
        }
    };

    $scope.numeroTavolo = null;

    $scope.updateSelectedProd = function(nomeProd){
        if($scope.numeroTavolo != null){
            var checkBox = document.getElementById("check.".concat(nomeProd));
            if(checkBox.checked) {
                $scope.actuallyChecked.push(checkBox);
                var ingrediente = {nome:nomeProd,       quantita:1,         tavolo:$scope.numeroTavolo};
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
            alert("inserisci numero tavolo");
            var checkBox = document.getElementById("check.".concat(nomeProd));
            checkBox.checked = false;
        }
    };


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
            alert("COMANDA INVIATA : " + status);
        });
        for(var k in $scope.actuallyChecked){
            $scope.actuallyChecked[k].checked = false;
        }
        $scope.actuallyChecked = [];
        $scope.selectedProd = [];
    };



    $scope.confermaTavolo = function() {
        for(var k in $scope.actuallyChecked){
            $scope.actuallyChecked[k].checked = false;
        }
        $scope.actuallyChecked = [];
        $scope.selectedProd = [];
        $scope.numeroTavolo = document.getElementById('tavolo').value;
    }



});