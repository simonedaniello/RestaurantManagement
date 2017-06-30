/**
 * Created by alberto on 20/06/17.
 */

myApp.controller("ricercaPietanzaController", function ($scope, ajaxService, RicercaPietanzaService, $location) {

    var pageLimit = 5;

    var getPietanzeListPage = function (url, params) {
        ajaxService.getResource(url, params).then(function (response) {
            $scope.listaPietanze = response.content;
            $scope.currentPage = params.page;
            $scope.listaPagine = setPaginationNavbar(response);
        }, function (response) {
            console.log(response);
        });
    };

    var getPietanzeNavigation = function (pagina) {
        var params = {page : pagina, size : 3, nome : $scope.searchNome, tags : $scope.associatedTags};
        getPietanzeListPage("http://localhost:8080/dish", params);
    };

    var setPaginationNavbar = function (response) {
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
        return pagine;
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
    getPietanzeNavigation(0);

    $scope.updatePietanzePagina = getPietanzeNavigation;
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
        ajaxService.deleteResource("http://localhost:8080/dish/delete/" + id.toString(), null).then(function (response) {
            getPietanzeNavigation(0);
            alert("Pietanza rimossa");
        }, function (response) {
            console.log(response);
        });
    };

    $scope.modificaPietanza = function (id) {
        $location.path("modificaPietanza/"+ id.toString());
    };

    $scope.ricerca = function () {
        getPietanzeNavigation(0);
    };
});
