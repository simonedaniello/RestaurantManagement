myApp.controller("modificaPietanzaController", function($scope, ajaxService, CreaPietanzaService, $http, $routeParams, $location) {


    var updateListaProdotti = function () {
        //ajaxService.getResource("http://localhost:8080/creaPietanza/getProdotti", null).then(
        $http.get("jsonFiles/prodotti.json").then(
            function (response) {
                //$scope.prodotti = CreaPietanzaService.parseProductList(response);
                var data = response.data;
                $scope.prodotti = data.prodotti;
                updateTagList();
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
                fillParametersToModify();
            }
            , function (response) {
                alert("Couldn't get tags");
            });
    };


    var fillParametersToModify = function () {
        var id = $routeParams.idPietanza;
        ajaxService.getResource("http://localhost:8080/dish/getById?id=" + id.toString(), null).then(function (response) {
            $scope.nomePietanza = response.nome;
            $scope.prezzoPietanza = response.prezzo;
            for (i in response.ingredienti){
                var checkBox = document.getElementById("check.".concat(response.ingredienti[i].prodotto.id));
                checkBox.checked = true;
                $scope.updateSelectedProd(response.ingredienti[i].prodotto.nome,response.ingredienti[i].prodotto.id, response.ingredienti[i].quantita);
            }
            for (i in response.etichette){
                var checkBox = document.getElementById("check.".concat(response.etichette[i].classificatore));
                checkBox.checked = true;
                $scope.updateSelectedTag(response.etichette[i].classificatore);
            }
        }, function (response) {
            console.log(response);
        })
    };

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

    $scope.updateSelectedProd = function(nomeProd, id, q){
        var checkBox = document.getElementById("check.".concat(id));
        if(checkBox.checked) {
            var ingrediente = {nomeProdotto:nomeProd, quantita:q, prodottoId: id};
            $scope.selectedProd.push(ingrediente);
        }else{
            var i = searchIndex(nomeProd);
            $scope.selectedProd.splice(i,1);
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

    $scope.updateDish = function(){
        var dtoPietanza = {nome: $scope.nomePietanza, prezzo: $scope.prezzoPietanza, etichette: $scope.associatedTags, ingredienti: $scope.selectedProd};
        var jsonPiet = angular.toJson(dtoPietanza);
        ajaxService.updateResource("http://localhost:8080/dish/" + $routeParams.idPietanza, jsonPiet).then(function (response) {
            alert("Pietanza modificata con successo");
            $location.path("cercaPietanza")
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
    };

});
