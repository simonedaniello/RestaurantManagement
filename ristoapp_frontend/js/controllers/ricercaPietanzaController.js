/**
 * Created by alberto on 20/06/17.
 */

myApp.controller("ricercaPietanzaController", function ($scope, ajaxService, RicercaPietanzaService, $location, $anchorScroll) {

    var pageLimit = 5;

    var getPietanzeListPage = function (url, pagina) {
        ajaxService.getResource("http://localhost:8080/dish/pietanze", {page : pagina, size : 3}).then(function (response) {
            $scope.listaPietanze = response.content;
            $scope.currentPage = pagina;
            $location.hash('top');
            $anchorScroll();
            $scope.listaPagine = setPaginationNavbar(response);
        }, function (response) {
            console.log(response);
        });
    };

    var initPage = function () {
        ajaxService.getResource("http://localhost:8080/dish/pietanze", {page : 0, size : 3}).then(function (response) {
            $scope.listaPietanze = response.content;
            $scope.currentPage = 0;
            $scope.listaPagine = setPaginationNavbar(response);
        }, function (response) {
            console.log(response);
        });
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

    var getPietanzeNavigation = function (pagina) {
        getPietanzeListPage("http://localhost:8080/dish/pietanze", pagina);
    };

    var getPietanzeSearch = function (pagina) {
        getPietanzeListPage("http://localhost:8080/dish", pagina);
    };

    $scope.updatePietanzePagina = getPietanzeNavigation;

    updateTagList();
    initPage();


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
            initPage();
        }, function (response) {
            console.log(response);
        });
    };

    $scope.ricerca = function () {
        //var url = RicercaPietanzaService.generateSearchURL($scope.searchNome, $scope.associatedTags);
        ajaxService.getResource("http://localhost:8080/dish", {page : 0, size : 3, nome : $scope.searchNome, tags : $scope.associatedTags}).then(function (response) {
            $scope.listaPietanze = response.content;
            $scope.currentPage = 0;
            $scope.listaPagine = setPaginationNavbar(response);
            $scope.updatePietanzePagina = getPietanzeSearch;
        }, function (response) {
            console.log(response);
        })
    }
});
