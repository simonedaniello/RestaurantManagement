myApp.controller("CreaPietanzaController", function($scope, ajaxService, CreaPietanzaService) {

    //prodotti e tag dovrebbero essere vuoti e riempiti con una richiesta al server
    $scope.prodotti = [
        {name:"uovo",                   id:1},
        {name:"sale",                   id:2},
        {name:"olio",                   id:3},
        {name:"pasta",                  id:4},
        {name:"aceto",                  id:5},
        {name:"zucchina",               id:6},
        {name:"prosciutto crudo",       id:7},
        {name:"pomodoro",               id:8}
    ];

    var updateListaProdotti = function () {
        ajaxService.getResource("http://localhost:8080/creaPietanza/getProdotti", null).then(
            function (response) {
                $scope.tags = CreaPietanzaService.parseProductList(response);
            }
            , function (response) {
                alert("Couldn't get products");
            });
    };

    updateListaProdotti();


    var updateTagList = function() {
        ajaxService.getResource("http://localhost:8080/creaPietanza/getTags", null).then(
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
            var ingrediente = {name:nomeProd,       quantita:0};
            $scope.selectedProd.push(ingrediente);
            $scope.selectedProd.sort(function(a, b){
                return (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0)
            });
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

    $scope.saveDish = function(){
        var dtoPietanza = {
            nome:$scope.nomePietanza,
            tagList: $scope.associatedTags,
            prodList: $scope.selectedProd
        };
        var jsonPiet = JSON.stringify(dtoPietanza);
        //CONTROLLA DEL URL SE PATH VA BENE
        ajaxService.sendResource("url", jsonPiet)
    };

    $scope.saveTag = function(){
        var dtoTag = {classificatore:$scope.nomeNewTag};
        var jsonTag = JSON.stringify(dtoTag);
        //CONTROLLA DEL URL SE PATH VA BENE
        ajaxService.sendResource("http://localhost:8080/creaPietanza/addTag", jsonTag).then(function (response) {
            updateTagList();
        }, function (response) {
            console.log(response)
        });
        $scope.nomeNewTag = "";
    }

});