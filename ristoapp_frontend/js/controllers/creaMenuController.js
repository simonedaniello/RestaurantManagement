
myApp.controller("creaMenuCtrl", function($scope, creaMenuService) {
    $scope.categorieMenu = [];

    $scope.nomeMenu = "";
    $scope.chef = "";

    $scope.pietanze = creaMenuService.getPietanze();

    $scope.categorie = creaMenuService.getCategorieList();

    $scope.tags = creaMenuService.getTagList();

    $scope.nomeCategoria = "";
    $scope.filtro = "all";

    $scope.selected = [];

    $scope.filterPietanze = function(element) {
        if ($scope.filtro === "all") {
            return true;
        }
        for(var i = 0, len = element.Tags.length; i < len; i++) {
            if (element.Tags[i] === $scope.filtro) {
                return true;
            }
        }
        return false;
    };


    var searchIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i].Name === searchTerm) {
                return i;
            }
        }
    };

    $scope.updateCheckboxFiltered = function(pietanza) {
        for(var i = 0, len = $scope.selected.length; i < len; i++) {
            //se la pietanza è tra le selezionate
            if ($scope.selected[i].Name === pietanza.Name) {
                //e il filtro è all sarà visibile e la devo chekkare
                if ($scope.filtro === "all") {
                    document.getElementById(pietanza.Name).checked = true;
                    return;
                }
                //oppure se ha il filtro selezionato sarà visibile e la devo chekkare
                for(var j = 0, lenT = $scope.selected[i].Tags.length; j < lenT; j++) {
                    if ($scope.selected[i].Tags[j] === $scope.filtro) {
                        document.getElementById(pietanza.Name).checked = true;
                        return;
                    }
                }
            }
        }
    };

    $scope.updateSelected = function(pietanza){
        var checkBox = document.getElementById(pietanza.Name);
        if(checkBox.checked) {
            $scope.selected.push(pietanza);
            $scope.selected.sort(function(a, b){return (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0)});
        }else{
            var i = searchIndex(pietanza.Name, $scope.selected);
            $scope.selected.splice(i,1);
        }
    };


    var uncheckAll = function(){
        for(var i = 0, len = $scope.selected.length; i < len; i++) {
            document.getElementById($scope.selected[i].Name).checked = false;
        }
        $scope.selected = [];
    };


    $scope.removeFromMenu = function(nomeCat){
        var i = searchIndex(nomeCat, $scope.categorieMenu);
        $scope.categorieMenu.splice(i,1);
    };

    $scope.addToMenu = function(){
        if ($scope.nomeCategoria === "") {
            $scope.errorMessageCat = "Specificare il nome della categoria.";
            return;
        }
        if ($scope.selected.length === 0) {
            $scope.errorMessageCat = "Devi selezionare almeno una pietanza che appartiene alla categoria.";
            return;
        }
        for(var i = 0, len = $scope.categorieMenu.length; i < len; i++) {
            if($scope.categorieMenu[i].Name === $scope.nomeCategoria) {
                $scope.errorMessageCat = "La categoria selezionata esiste già nel menu.";
                return;
            }
        }
        var newCategory = {Name: $scope.nomeCategoria, Pietanze:$scope.selected};
        $scope.categorieMenu.push(newCategory);
        $scope.nomeCategoria = "";
        uncheckAll();
        $scope.filtro = "all";
    };


    $scope.resetCat = function(){
        $scope.categorieMenu.splice(0, $scope.categorieMenu.length);
    };


    $scope.saveMenu = function(){
        if ($scope.categorieMenu.length === 0) {
            $scope.errorMessageMenu = "Il menu deve contenere almeno una categoria.";
            return;
        }
        if ($scope.nomeMenu === "") {
            $scope.errorMessageMenu = "Specificare nome del menu.";
            return;
        }
        if ($scope.chef === "") {
            $scope.errorMessageMenu = "Indicare lo chef creatore del menu.";
            return;
        }

        /*implementare passaggio dati al server*/
        var dtoMenu = {
            nome:$scope.nomeMenu,
            chef: $scope.chef,
            categorieMenu: $scope.categorieMenu
        };
        var jsonMenu = JSON.stringify(dtoMenu);
        ajaxService.sendResource("url", jsonPiet)
        /*implementare passaggio dati al server
        **/
    };
});