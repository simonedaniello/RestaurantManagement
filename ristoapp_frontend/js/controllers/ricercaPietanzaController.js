/**
 * Created by alberto on 20/06/17.
 */

myApp.controller("ricercaPietanzaController", function ($scope, ajaxService, RicercaPietanzaService) {

    $scope.showList = true;

    var getPietanzeList = function () {
        ajaxService.getResource("http://localhost:8080/dish", null).then(function (response) {
            $scope.listaPietanze = response;
        }, function (response) {
            console.log(response);
        });
    };

    var updateTagList = function() {
        ajaxService.getResource("http://localhost:8080/tags", null).then(
            function (response) {
                $scope.tags = RicercaPietanzaService.parseTagArray(response);
            }
            , function (response) {
                alert("Couldn't get tags");
            });
    };

    updateTagList();
    getPietanzeList();


    $scope.associatedTags = [];

    $scope.updateSelectedTag = function(nomeTag){
        var checkBox = document.getElementById("check.".concat(nomeTag));
        if(checkBox.checked) {
            $scope.associatedTags.push(nomeTag);
            $scope.associatedTags.sort()
        }else{
            $scope.associatedTags.splice($scope.associatedTags.indexOf(nomeTag),1);
        }
    };

    $scope.deletePietanza = function (id) {
        console.log("OK");
        ajaxService.getResource("http://localhost:8080/dish/delete/" + id.toString(), null).then(function (response) {
            getPietanzeList();
        }, function (response) {
            console.log(response);
        });
    };
});
