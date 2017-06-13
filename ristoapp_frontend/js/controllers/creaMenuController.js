
myApp.controller("creaMenuCtrl", function($scope, ajaxService, creaMenuService, CreaPietanzaService) {
    $scope.categorieMenu = [];
    $scope.nomeMenu = "";
    //$scope.chef = "";
    $scope.nomeCategoria = "";
    $scope.descrizione = "";
    $scope.filtro = "all";
    $scope.selected = [];
    $scope.isModify = 0;
    /*
    PROBLEMA
    se aggiungi una pietanza poi premi modifica, poi chiudi modal senza modificarla, da li in poi puoi aggiugnere duplicati
    servirebbe eseguire una funzione al dismiss del modal che setta $scope.isModify = 0;

    stesso problema se premi modifica poi fai dismiss del modal e poi premi aggiungi categoria ti sovrascrive quella che avevi premuto modifica prima
    serve che al dismiss del modal resetti tutto
     */

    $scope.pietanze = creaMenuService.getPietanze();
    /*var getPietanzeList = function() {
        ajaxService.getResource("http://localhost:8080/createMenu/getDishes", null).then(
            function (response) {
                $scope.pietanze = creaMenuService.parsePietanzeArray(response);
            }
        , function (response) {
            alert("Couldn't get dishes");
        });
     };
     getPietanzeList();*/

    var getTagList = function() {
        ajaxService.getResource("http://localhost:8080/tags", null).then(
            function (response) {
                $scope.etichette = CreaPietanzaService.parseTagArray(response);
            }
            , function (response) {
                alert("Couldn't get tags");
            });
    };
    getTagList();

    var getCatNamesList = function() {
        ajaxService.getResource("http://localhost:8080/categoriesNames", null).then(
            function (response) {
                $scope.categorie = response;
            }
            , function (response) {
                alert("Couldn't get categories names");
            });
    };
    getCatNamesList();

    $scope.filterPietanze = function(element) {
        if ($scope.filtro === "all") {
            return true;
        }
        for(var i = 0, len = element.etichette.length; i < len; i++) {
            if (element.etichette[i] === $scope.filtro) {
                return true;
            }
        }
        return false;
    };


    var searchIndex = function(searchTerm, array){
        for(var i = 0, len = array.length; i < len; i++) {
            if (array[i].nome === searchTerm) {
                return i;
            }
        }
    };

    $scope.updateCheckboxFiltered = function(pietanza) {
        for(var i = 0, len = $scope.selected.length; i < len; i++) {
            //se la pietanza è tra le selezionate
            if ($scope.selected[i].nome === pietanza.nome) {
                //e il filtro è all sarà visibile e la devo chekkare
                if ($scope.filtro === "all") {
                    document.getElementById(pietanza.nome).checked = true;
                    return;
                }
                //oppure se ha il filtro selezionato sarà visibile e la devo chekkare
                for(var j = 0, lenT = $scope.selected[i].etichette.length; j < lenT; j++) {
                    if ($scope.selected[i].etichette[j] === $scope.filtro) {
                        document.getElementById(pietanza.nome).checked = true;
                        return;
                    }
                }
            }
        }
    };

    $scope.updateSelected = function(pietanza){
        var checkBox = document.getElementById(pietanza.nome);
        if(checkBox.checked) {
            $scope.selected.push(pietanza);
            $scope.selected.sort(function(a, b){return (a.nome > b.nome) ? 1 : ((b.nome > a.nome) ? -1 : 0)});
        }else{
            var i = searchIndex(pietanza.nome, $scope.selected);
            $scope.selected.splice(i,1);
        }
    };


    var uncheckAll = function(){
        for(var i = 0, len = $scope.selected.length; i < len; i++) {
            //se la selezionata ha il filtro
            for(var j = 0, lenJ = $scope.selected[i].etichette.length; j < lenJ; j++) {
                if ($scope.selected[i].etichette[j] === $scope.filtro || $scope.filtro === "all") {
                    document.getElementById($scope.selected[i].nome).checked = false;
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
        delete $scope.errorMessageCat;
    };

    $scope.resetActCat = function () {
        resetActualCategory();
    };

    $scope.modifyCat = function(nomeCat){
        var i = searchIndex(nomeCat, $scope.categorieMenu);
        resetActualCategory();
        $scope.nomeCategoria = $scope.categorieMenu[i].nome;
        $scope.selected = $scope.categorieMenu[i].pietanze.slice();
        for(var j = 0, len = $scope.categorieMenu[i].pietanze.length; j < len; j++) {
            document.getElementById($scope.categorieMenu[i].pietanze[j].nome).checked = true;
        }
        $scope.isModify = 1;
    };


    $scope.removeFromMenu = function(nomeCat){
        var i = searchIndex(nomeCat, $scope.categorieMenu);
        $scope.categorieMenu.splice(i,1);
    };


    $scope.moveUp = function(nomeCat){
        var i = searchIndex(nomeCat, $scope.categorieMenu);
        if (i === 0) return;
        var temp = $scope.categorieMenu[i];
        $scope.categorieMenu[i] = $scope.categorieMenu[i-1];
        $scope.categorieMenu[i-1] = temp;
    };


    $scope.moveDown = function(nomeCat){
        var i = searchIndex(nomeCat, $scope.categorieMenu);
        if (i === $scope.categorieMenu.length-1) return;
        var temp = $scope.categorieMenu[i];
        $scope.categorieMenu[i] = $scope.categorieMenu[i+1];
        $scope.categorieMenu[i+1] = temp;
    };

    $scope.addToMenu = function(){
        console.log($scope.isModify);
        if ($scope.isModify === 0) {
            for(var i = 0, len = $scope.categorieMenu.length; i < len; i++) {
                if($scope.categorieMenu[i].nome === $scope.nomeCategoria) {
                    $scope.errorMessageCat = "La categoria selezionata esiste già nel menu.";
                    return;
                }
            }
        }
        if ($scope.nomeCategoria === "") {
            $scope.errorMessageCat = "Specificare il nome della categoria.";
            return;
        }
        if ($scope.selected.length === 0) {
            $scope.errorMessageCat = "Devi selezionare almeno una pietanza che appartiene alla categoria.";
            return;
        }
        var newCategory = {nome: $scope.nomeCategoria, pietanze:$scope.selected};
        resetActualCategory();
        if ($scope.isModify === 1) {
            var j = searchIndex($scope.nomeCategoria, $scope.categorieMenu);
            $scope.categorieMenu.splice(j,1);
            $scope.categorieMenu.splice(j,0,newCategory);
            $scope.isModify = 0;
            return;
        }
        $scope.categorieMenu.push(newCategory);
        delete $scope.errorMessageCat;
    };


    $scope.resetCat = function(){
        $scope.categorieMenu.splice(0, $scope.categorieMenu.length);
        $scope.nomeCategoria = "";
        $scope.descrizione = "";
        delete $scope.errorMessageMenu;
    };


    $scope.saveMenu = function(isAct){
        console.log(isAct);
        if ($scope.categorieMenu.length === 0) {
            $scope.errorMessageMenu = "Il menu deve contenere almeno una categoria.";
            return;
        }
        if ($scope.nomeMenu === "") {
            $scope.errorMessageMenu = "Specificare nome del menu.";
            return;
        }
        /*if ($scope.chef === "") {
            $scope.errorMessageMenu = "Indicare lo chef creatore del menu.";
            return;
        }*/
        var dtoMenu = {
            nome:$scope.nomeMenu,
            //chef: $scope.chef,
            categorie: $scope.categorieMenu,
            isActive: isAct,
            descrizione: $scope.descrizione
        };
        var jsonMenu = JSON.stringify(dtoMenu);
        ajaxService.sendResource("http://localhost:8080/menu", jsonMenu).then(function (response) {
            location.href = "#";
            delete $scope.errorMessageMenu;
        }, function (response) {
            $scope.errorMessageMenu = "Errore nell'invio dei dati al server.";
        });
    };
});