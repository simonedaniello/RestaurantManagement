myApp.controller("GestisciTagController", function($scope, EtichettaService) {

    $scope.nomeNewTag = "";
    $scope.nomeUpdateTag = "";
    $scope.tags = EtichettaService.getTag();

    $scope.saveTag = function(){
        if ($scope.nomeNewTag === "") {
            alert("Specificare il nome del tag nuovo da creare.");
            return;
        }
        $scope.tags = EtichettaService.postTag($scope.nomeNewTag);
        $scope.nomeNewTag = "";
    };

    $scope.modifyTag = function(tagName){
        var updatedName = document.getElementById(tagName).value;
        EtichettaService.putTag(tagName, updatedName);
        $scope.tags = EtichettaService.getTag();
    };

    $scope.deleteTag = function(tagName){
        EtichettaService.deleteTag(tagName);
        $scope.tags = EtichettaService.getTag();
    };
});



/* VECCHIA IMPLEMENTAZIONE SENZA SERVICE E CON TUTTO IN QUESTO FILE
myApp.controller("GestisciTagController", function($scope, ajaxService, CreaPietanzaService) {

    $scope.nomeNewTag = "";
    $scope.nomeUpdateTag = "";

    var updateTagList = function() {
        zajaxService.getResource("http://localhost:8080/tags", null).then(
            function (response) {
                $scope.tags = CreaPietanzaService.parseTagArray(response);
            }
            , function (response) {
                alert("Couldn't get tags");
            });
    };
    updateTagList();

    var searchIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i] === searchTerm) {
                return i;
            }
        }
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

    $scope.modifyTag = function(tagName){
        var updatedName = document.getElementById(tagName).value;
        //tagName è
        //non vado avanti perche non so come implementerete la update
        ajaxService.getResource("http://localhost:8080/tags/update/" + tagName.toString(), null).then(function (response) {
            var i = searchIndex(tagName, $scope.tags);
            updateTagList();
        }, function (response) {
            alert("Couldn't update tags");
            console.log(response);
        });
    };

    $scope.deleteTag = function(tagName){
        var dtoEtichetta = {
         classificatore: tagName
         };
         var jsonEtichetta = JSON.stringify(dtoEtichetta);
        ajaxService.getResource("http://localhost:8080/tags/delete/" + tagName.toString(), null).then(function (response) {
            var i = searchIndex(tagName, $scope.tags);
            updateTagList();
        }, function (response) {
            alert("Couldn't delete tags");
            console.log(response);
        });
    };
});
*/