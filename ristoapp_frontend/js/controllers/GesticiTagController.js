myApp.controller("GestisciTagController", function($scope, ajaxService) {

    $scope.nomeNewTag = "";
    $scope.nomeUpdateTag = "";

    var parseResponse = function(resp) {
        var array = [];
        for (i in resp){
            array.push(resp[i].classificatore);
        }
        return array;
    };

    var updateTagList = function() {
        ajaxService.getResource("https://localhost:8080/tags", null).then(
            function (response) {
                $scope.tags = parseResponse(response);
            }
            , function (response) {
                alert("Couldn't get tags");
            });
    };
    updateTagList();

    $scope.saveTag = function(){
        if ($scope.nomeNewTag === "") {
            alert("Specificare il nome del tag nuovo da creare.");
            return;
        }
        var dtoTag = {classificatore: $scope.nomeNewTag};
        var jsonTag = JSON.stringify(dtoTag);
        ajaxService.sendResource("https://localhost:8080/tags", jsonTag).then(
            function (response) {
                updateTagList();
            }
            ,function (response) {
                alert("Couldn't save tag");
            });
        $scope.nomeNewTag = "";
    };

    $scope.deleteTag = function(tagName){
        ajaxService.deleteResource("https://localhost:8080/tags/" + tagName.toString(), null).then(
            function (response) {
                updateTagList();
            }
            ,function (response) {
                alert("Couldn't delete tag");
            });
    };

    $scope.modifyTag = function(tagName){
        var updatedName = document.getElementById(tagName).value;
        if (updatedName === "") {
            alert("Specificare un nome non vuoto per la modifica.");
            return;
        }
        var dtoTag = {classificatore: updatedName};
        var jsonTag = JSON.stringify(dtoTag);
        ajaxService.updateResource("https://localhost:8080/tags/" + tagName.toString(), jsonTag).then(
            function (response) {
                updateTagList();
            }
            ,function (response) {
                alert("Couldn't update tag");
            });
    };
});