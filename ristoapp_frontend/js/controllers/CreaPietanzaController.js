myApp.controller("CreaPietanzaController", function($scope, ajaxService, CreaPietanzaService, $http) {


    var updateListaProdotti = function () {
        //ajaxService.getResource("http://localhost:8080/creaPietanza/getProdotti", null).then(
        $http.get("jsonFiles/prodotti.json").then(
            function (response) {
                //$scope.prodotti = CreaPietanzaService.parseProductList(response);
                var data = response.data;
                $scope.prodotti = data.prodotti;
            }
        , function (response) {
                alert("Couldn't get products");
            });
    };

    updateListaProdotti();


    var updateTagList = function() {
        ajaxService.getResource("http://localhost:8080/tags", null).then(
            function (response) {
                $scope.tags = CreaPietanzaService.parseTagArray(response);
            }
            , function (response) {
                alert("Couldn't get tags");
            });
    };

    updateTagList();

    $scope.selectedProd = [];
    $scope.associatedTags = [];
    $scope.nomePietanza = "";
    $scope.nomeNewTag = "";
    $scope.prodottiTot = 0;
    $scope.prod = [];

    var searchIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i].nomeProdotto === searchTerm) {
                return i;
            }
        }
    };

    $scope.changeQnt = function(nomeProd) {
        var qnt = document.getElementById("quant.".concat(nomeProd)).value;
        var j = searchIndex(nomeProd, $scope.prod);
        var prezzoUn = $scope.prod[j].prezzo;
        console.log("actual qnt, old qnt, price");
        console.log(qnt);
        console.log($scope.prod[j].qnt);
        console.log(prezzoUn);
        var diff = $scope.prod[j].qnt - qnt;
        $scope.prod[j].qnt = qnt;
        if (diff < 0) { //aggiunto
            $scope.prodottiTot += prezzoUn;
        } else { //tolto
            $scope.prodottiTot -= prezzoUn;
        }
    };

    $scope.updateSelectedProd = function(nomeProd, id, prezzo){
        var checkBox = document.getElementById("check.".concat(nomeProd));
        if(checkBox.checked) {
            var ingrediente = {nomeProdotto:nomeProd, quantita:1, prodottoId: id};
            $scope.selectedProd.push(ingrediente);
            var p = {prezzo: prezzo, nomeProdotto: nomeProd, qnt: 1};
            $scope.prod.push(p);
            $scope.prodottiTot += prezzo;
        }else{
            var i = searchIndex(nomeProd, $scope.selectedProd);
            $scope.selectedProd.splice(i,1);
            var j = searchIndex(nomeProd, $scope.prod);
            $scope.prod.splice(j,1);
            $scope.prodottiTot -= prezzo*$scope.prod[j].qnt;
        }
    };

    $scope.updateSelectedTag = function(nomeTag){
        var checkBox = document.getElementById("check.".concat(nomeTag));
        if(checkBox.checked) {
            $scope.associatedTags.push(nomeTag);
            $scope.associatedTags.sort()
        }else{
            $scope.associatedTags.splice($scope.associatedTags.indexOf(nomeTag),1);
        }
    };

    $scope.saveDish = function(){
        var dtoPietanza = {nome: $scope.nomePietanza, prezzo: $scope.prezzoPietanza, etichette: $scope.associatedTags, ingredienti: $scope.selectedProd};
        var jsonPiet = angular.toJson(dtoPietanza);
        ajaxService.sendResource("http://localhost:8080/dish", jsonPiet).then(function (response) {
        }, function (response) {
            console.log(response)
        });
    };

    $scope.saveTag = function(){
        if ($scope.nomeNewTag === "") {
            alert("Specificare il nome del tag nuovo da creare.");
            return;
        }
        var dtoTag = {classificatore:$scope.nomeNewTag};
        var jsonTag = JSON.stringify(dtoTag);
        ajaxService.sendResource("http://localhost:8080/tags", jsonTag).then(function (response) {
            updateTagList();
        }, function (response) {
            alert("Couldn't save tag");
            console.log(response)
        });
        $scope.nomeNewTag = "";
    }

});