myApp.controller("modificaPietanzaController", function($scope, ajaxService, CreaPietanzaService, $http, $routeParams, $location) {


    var updateListaProdotti = function () {
        //ajaxService.getResource("http://localhost:8080/creaPietanza/getProdotti", null).then(
        $http.get("jsonFiles/prodotti.json").then(
            function (response) {
                //$scope.prodotti = CreaPietanzaService.parseProductList(response);
                var data = response.data;
                $scope.prodotti = data.prodotti;
                console.log($scope.prodotti);
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
                var checkBox = document.getElementById("check.".concat(i.prodotto.id));
                checkBox.checked = true;
                console.log(i)
                $scope.updateSelectedProd(i.prodotto.nome,
                    response.ingredienti[i].prodotto.id, response.ingredienti[i].quantita, response.ingredienti[i].prezzo);
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
    $scope.prod = [];
    $scope.associatedTags = [];
    $scope.nomePietanza = "";
    $scope.nomeNewTag = "";
    $scope.prodottiTot = 0;


    var searchIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i].nome === searchTerm) {
                return i;
            }
        }
    };

    $scope.updateSelectedProd = function(nomeProd, id, q, prezzo){
        var checkBox = document.getElementById("check.".concat(id));
        if(checkBox.checked) {
            var ingrediente = {nome:nomeProd, quantita:1, prodottoId: id};
            $scope.selectedProd.push(ingrediente);
            var p = {prezzo: prezzo, nome: nomeProd, qnt: 1};
            $scope.prod.push(p);
            $scope.prodottiTot = ($scope.prodottiTot*10 + prezzo*10)/10;
        }else{
            var i = searchIndex(nomeProd, $scope.selectedProd);
            $scope.selectedProd.splice(i,1);
            var j = searchIndex(nomeProd, $scope.prod);
            $scope.prodottiTot = ($scope.prodottiTot*10 - prezzo*($scope.prod[j].qnt)*10)/10;
            $scope.prod.splice(j,1);
        /*
             var ingrediente = {nome:nomeProd, quantita:q, prodottoId: id};
             $scope.selectedProd.push(ingrediente);
             console.log($scope.prodottiTot);
             $scope.prodottiTot = ($scope.prodottiTot*10 + prezzo*10)/10;
             }else{
             var j = searchIndex(nomeProd, $scope.selectedProd);
             $scope.prodottiTot = ($scope.prodottiTot*10 - prezzo*($scope.prod[j].qnt)*10)/10;

             $scope.selectedProd.splice(j,1);
             console.log($scope.selectedProd);
             }*/
        }
    };

    $scope.changeQnt = function(nomeProd) {
        var qnt = document.getElementById("quant.".concat(nomeProd)).value;
        var j = searchIndex(nomeProd, $scope.prod);
        var prezzoUn = $scope.prod[j].prezzo;
        var diff = $scope.prod[j].qnt - qnt;
        $scope.prod[j].qnt = qnt;
        if (diff < 0) { //aggiunto
            $scope.prodottiTot = ($scope.prodottiTot*10 + prezzoUn*10)/10;
        } else { //tolto
            $scope.prodottiTot = ($scope.prodottiTot*10 - prezzoUn*10)/10;
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
        });
        $scope.nomeNewTag = "";
    };

});


