myApp.controller("GestisciTagController", function($scope, ajaxService, CreaPietanzaService) {

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

    var searchIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i] === searchTerm) {
                return i;
            }
        }
    };

    $scope.modifyTag = function(tagName){
    };

    $scope.deleteTag = function(tagName){
        /*var dtoEtichetta = {
            classificatore: tagName
        };
        var jsonEtichetta = JSON.stringify(dtoEtichetta);*/
        ajaxService.getResource("http://localhost:8080/tags/delete/" + tagName.toString(), null).then(function (response) {
            var i = searchIndex(tagName, $scope.tags);
            updateTagList();
        }, function (response) {
            alert("Couldn't delete tags");
            console.log(response);
        });
    };
});
