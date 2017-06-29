/**
 * Created by alberto on 20/06/17.
 */

myApp.controller("ricercaPietanzaController", function ($scope, ajaxService, RicercaPietanzaService) {

    var pageLimit = 5;

    var getPietanzeList = function (pagina) {
        ajaxService.getResource("http://localhost:8080/dish/pietanze?page=" + pagina.toString() + "&size=3", null).then(function (response) {
            $scope.listaPietanze = response.content;
            $scope.currentPage = pagina;
            var limit = $scope.currentPage + pageLimit;
            var pagine = [];
            if (limit < response.totalPages){
                $scope.nextPage = false;
                for (var i = $scope.currentPage; i < limit; i++) pagine.push(i);
            }
            else {
                $scope.nextPage = true;
                var i = $scope.currentPage - (limit -response.totalPages);
                if (i < 0) i = 0;
                for (; i < response.totalPages; i++) pagine.push(i);
            }
            $scope.listaPagine = pagine;
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

    $scope.updatePietanzePagina = getPietanzeList;

    updateTagList();
    getPietanzeList(0);


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
        ajaxService.deleteResource("http://localhost:8080/dish/delete/" + id.toString(), null).then(function (response) {
            getPietanzeList();
        }, function (response) {
            console.log(response);
        });
    };
});
