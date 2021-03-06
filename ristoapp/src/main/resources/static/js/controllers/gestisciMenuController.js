
myApp.controller("creaMenuCtrl", function($scope, ajaxService, CreaPietanzaService, $routeParams) {
    $scope.categorieMenu = [];
    $scope.nomeMenu = "";
    $scope.nomeCategoria = "";
    $scope.descrizione = "";
    $scope.filtro = "all";
    $scope.selected = [];
    $scope.isModify = 0;
    $scope.cateToModifiy = "";
    $scope.categorie = [];

    var getPietanzeList = function() {
        ajaxService.getResource("https://localhost:8080/dish/getAll", null).then(
            function (response) {
                $scope.pietanze = response.slice();
            }
        , function (response) {
            alert("Couldn't get dishes");
        });
     };
     getPietanzeList();

    var getTagList = function() {
        ajaxService.getResource("https://localhost:8080/tags", null).then(
            function (response) {
                $scope.etichette = CreaPietanzaService.parseTagArray(response);
            }
            , function (response) {
                alert("Couldn't get tags");
            });
    };
    getTagList();

    var getCatNamesList = function() {
        ajaxService.getResource("https://localhost:8080/categoriesNames", null).then(
            function (response) {
                for (i in response){
                    $scope.categorie.push(response[i].nome);
                }
            }
            , function (response) {
                alert("Couldn't get categories names");
            });
    };
    getCatNamesList();

    var fillParametersToModify = function () {
        var nameMenu = $routeParams.nomeMenu;
        if (nameMenu === undefined) return;
        ajaxService.getResource("https://localhost:8080/menu/" + nameMenu, null).then(function (response) {
            $scope.nomeMenu = response.nomeMenu;
            $scope.descrizione = response.descrizione;
            //parseCategorie(response.categorie);
            $scope.categorieMenu = response.categorie;
            if (response.isActive) {
                document.getElementById("active").checked = true;
            }
        }, function (response) {
            alert("Couldn't fill parameters");
        })
    };
    fillParametersToModify();

    $scope.filterPietanze = function(element) {
        if ($scope.filtro === "all") {
            return true;
        }
        for(var i = 0, len = element.pietanzaDto.etichette.length; i < len; i++) {
            if (element.pietanzaDto.etichette[i] === $scope.filtro) {
                return true;
            }
        }
        return false;
    };

    var searchIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i].pietanzaDto.nome === searchTerm) {
                return i;
            }
        }
    };

    $scope.updateCheckboxFiltered = function(pietanza) {
        for(var i = 0, len = $scope.selected.length; i < len; i++) {
            //se la pietanza è tra le selezionate
            if ($scope.selected[i].pietanzaDto.nome === pietanza.pietanzaDto.nome) {
                //e il filtro è all sarà visibile e la devo chekkare
                if ($scope.filtro === "all") {
                    document.getElementById(pietanza.pietanzaDto.nome).checked = true;
                    return;
                }
                //oppure se ha il filtro selezionato sarà visibile e la devo chekkare
                for(var j = 0, lenT = $scope.selected[i].pietanzaDto.etichette.length; j < lenT; j++) {
                    if ($scope.selected[i].pietanzaDto.etichette[j] === $scope.filtro) {
                        document.getElementById(pietanza.pietanzaDto.nome).checked = true;
                        return;
                    }
                }
            }
        }
    };

    $scope.updateSelected = function(pietanza){
        var checkBox = document.getElementById(pietanza.pietanzaDto.nome);
        if(checkBox.checked) {
            $scope.selected.push(pietanza);
        }else{
            var i = searchIndex(pietanza.pietanzaDto.nome, $scope.selected);
            $scope.selected.splice(i,1);
        }
    };


    var uncheckAll = function(){
        for(var i = 0, len = $scope.selected.length; i < len; i++) {
            if ($scope.filtro === "all") {
                document.getElementById($scope.selected[i].pietanzaDto.nome).checked = false;
                continue;
            }
            for(var j = 0, lenJ = $scope.selected[i].pietanzaDto.etichette.length; j < lenJ; j++) {
                if ($scope.selected[i].pietanzaDto.etichette[j] === $scope.filtro) {
                    document.getElementById($scope.selected[i].pietanzaDto.nome).checked = false;
                    break;
                }
            }
        }
        $scope.selected = [];
    };


    var resetActualCategory = function(){
        $scope.nomeCategoria = "";
        uncheckAll();
        $scope.filtro = "all";
        $scope.isModify = 0;
        $scope.cateToModifiy = "";
    };

    $scope.resetActCat = function () {
        resetActualCategory();
    };

    $scope.modifyCat = function(nomeCat){
        var i = searchCategoriaIndex(nomeCat, $scope.categorieMenu);
        resetActualCategory();
        $scope.nomeCategoria = $scope.categorieMenu[i].nomeCategoria;
        $scope.selected = $scope.categorieMenu[i].pietanze.slice();
        for(var j = 0, len = $scope.categorieMenu[i].pietanze.length; j < len; j++) {
            document.getElementById($scope.categorieMenu[i].pietanze[j].pietanzaDto.nome).checked = true;
        }
        $scope.isModify = 1;
        $scope.cateToModifiy = nomeCat;
    };


    $scope.removeFromMenu = function(nomeCat){
        var i = searchCategoriaIndex(nomeCat, $scope.categorieMenu);
        $scope.categorieMenu.splice(i,1);
    };


    $scope.moveUp = function(nomeCat){
        var i = searchCategoriaIndex(nomeCat, $scope.categorieMenu);
        if (i === 0) return;
        var temp = $scope.categorieMenu[i];
        $scope.categorieMenu[i] = $scope.categorieMenu[i-1];
        $scope.categorieMenu[i-1] = temp;
    };


    $scope.moveDown = function(nomeCat){
        var i = searchCategoriaIndex(nomeCat, $scope.categorieMenu);
        if (i === $scope.categorieMenu.length-1) return;
        var temp = $scope.categorieMenu[i];
        $scope.categorieMenu[i] = $scope.categorieMenu[i+1];
        $scope.categorieMenu[i+1] = temp;
    };

    var searchCategoriaIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i].nomeCategoria === searchTerm) {
                return i;
            }
        }
    };

    $scope.addToMenu = function(){
        if ($scope.isModify === 0) {
            for (var i = 0, len = $scope.categorieMenu.length; i < len; i++) {
                if ($scope.categorieMenu[i].nomeCategoria === $scope.nomeCategoria) {
                    alert("La categoria selezionata esiste già nel menu.");
                    return;
                }
            }
        }
        if ($scope.isModify === 1) {
            for (i = 0, len = $scope.categorieMenu.length; i < len; i++) {
                if ($scope.categorieMenu[i].nomeCategoria === $scope.nomeCategoria && !($scope.categorieMenu[i].nomeCategoria === $scope.cateToModifiy)) {
                    alert("La categoria selezionata esiste già nel menu.");
                    return;
                }
            }
        }
        if ($scope.nomeCategoria === "") {
            alert("Specificare il nome della categoria.");
            return;
        }
        if ($scope.selected.length === 0) {
            alert("Devi selezionare almeno una pietanza che appartiene alla categoria.");
            return;
        }
        var newCategory = {nomeCategoria: $scope.nomeCategoria, pietanze:$scope.selected, id: null};
        if ($scope.isModify === 1) {
            var j = searchCategoriaIndex($scope.nomeCategoria, $scope.categorieMenu);
            newCategory.id = $scope.categorieMenu[j].id;
            $scope.categorieMenu.splice(j,1);
            $scope.categorieMenu.splice(j,0,newCategory);
            resetActualCategory();
            return;
        }
        $scope.categorieMenu.push(newCategory);
        resetActualCategory();
    };


    $scope.resetCat = function(){
        $scope.categorieMenu.splice(0, $scope.categorieMenu.length);
        $scope.nomeCategoria = "";
        $scope.descrizione = "";
    };


    var checkFields = function () {
        if ($scope.categorieMenu.length === 0) {
            alert("Il menu deve contenere almeno una categoria.");
            return null;
        }
        if ($scope.nomeMenu === "") {
            alert("Specificare nome del menu.");
            return null;
        }
        if ($scope.descrizione === "") {
            alert("Inserire una breve descrizione del menu.");
            return null;
        }
        var isAct = false;
        if (document.getElementById("active").checked === true) {
            isAct = true;
        }
        return isAct;
    };

    $scope.saveMenu = function(){
        var isAct = checkFields();
        if (isAct === null) {
            return;
        }
        var dtoMenu = {
            nome:$scope.nomeMenu,
            categorie: $scope.categorieMenu,
            isActive: isAct,
            descrizione: $scope.descrizione
        };
        var jsonMenu = JSON.stringify(dtoMenu);
        ajaxService.sendResource("https://localhost:8080/menu", jsonMenu).then(function (response) {
            location.href = "#";
        }, function (response) {
            alert("Errore nell'invio dei dati al server.");
        });
    };

    $scope.updateMenu = function () {
        var isAct = checkFields();
        if (isAct === null) {
            return;
        }
        var dtoMenu = {
            nome:$scope.nomeMenu,
            categorie: $scope.categorieMenu,
            isActive: isAct,
            descrizione: $scope.descrizione
        };
        console.log(dtoMenu);
        var jsonMenu = JSON.stringify(dtoMenu);
        ajaxService.updateResource("https://localhost:8080/menu", jsonMenu).then(function (response) {
            location.href = "#";
        }, function (response) {
            alert("Errore nell'invio dei dati al server.");
        });
    }

});