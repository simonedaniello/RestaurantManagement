
myApp.controller("creaCategoriaController", function($scope, creaMenuService) {
    $scope.pietanze = creaMenuService.getPietanze();
    $scope.pietanze.sort();

    $scope.pietanzeFiltrate = $scope.pietanze.slice();
    $scope.pietanzeFiltrate.sort();

    $scope.categorie = creaMenuService.getCategorieList();
    $scope.categorie.sort();

    $scope.tags = creaMenuService.getTagList();
    $scope.tags.sort();

    $scope.nomeCategoria = "";
    $scope.filtro = "";

    $scope.selected = [];

    var searchIndex = function(searchTerm){
        for(var i = 0, len = $scope.selected.length; i < len; i++) {
            if ($scope.selected[i].name === searchTerm) {
                return i;
            }
        }
    };

    $scope.updateSelected = function(pietanza){
        var checkBox = document.getElementById(pietanza.Name);
        if(checkBox.checked) {
            $scope.selected.push(pietanza);
            $scope.selected.sort(function(a, b){
                return (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0)
            });
        }else{
            var i = searchIndex(pietanza);
            $scope.selected.splice(i,1);
        }
    };

    $scope.updateVisibleElements = function(){
        if ($scope.filtro === "all") {
            $scope.pietanzeFiltrate = $scope.pietanze.slice();
            $scope.pietanzeFiltrate.sort();
            return;
        }
        var list = [];
        for(var i = 0, lenP = $scope.pietanze.length; i < lenP; i++) {
            for(var j = 0, lenT = $scope.pietanze[i].Tags.length; j < lenT; j++) {
                if ($scope.pietanze[i].Tags[j] === $scope.filtro) {
                    list.push($scope.pietanze[i]);
                    break;
                }
            }
        }
        $scope.pietanzeFiltrate = list;
        $scope.pietanzeFiltrate.sort();
    };

    $scope.addToMenu = function(){
        if ($scope.nomeCategoria === "") {
            $scope.errorMessage = "Specificare il nome della categoria.";
            return;
        }
        if ($scope.selected.length === 0) {
            $scope.errorMessage = "Devi selezionare almeno una pietanza che appartiene alla categoria.";
            return;
        }
        var newCategory = {Name: $scope.nomeCategoria, Pietanze:$scope.selected};
        creaMenuService.addCategoryToMenu(newCategory);
    };
});