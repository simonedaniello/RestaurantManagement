

myApp.controller("creaMenuCtrl", function($scope, creaMenuService) {
    $scope.categorieMenu = [];

    /*$scope.categorie =[
     {"name": "Primi",
     "pietanze": [
     {
     "name": "Spaghetti allo scoglio",
     "prezzo": 13,
     "prodotti": ["spaghetti", "cozze", "seppie", "calamari", "gamberetti", "aglio", "prezzemolo"]
     },
     {
     "name": "Tagliatelle ai funghi porcini",
     "prezzo": 12,
     "prodotti": ["pasta all'uovo", "funghi porcini", "panna", "prezzemolo"]
     },
     {
     "name": "Pasta al pesto",
     "prezzo": 7,
     "prodotti": ["pasta", "basilico", "pinoli", "parmigiano", "olio d'oliva"]
     }
     ]
     },
     {"name": "Secondi",
     "pietanze": [
     {
     "name": "Cotoletta alla milanese",
     "prezzo": 12,
     "prodotti": ["vitello", "pomodori", "rucola"]
     },
     {
     "name": "Fritto misto di mare",
     "prezzo": 16,
     "prodotti": ["totani", "gamberi", "scampi", "seppie"]
     },
     {
     "name": "Fritto misto di verdure*",
     "prezzo": 7,
     "prodotti": ["zucchine", "carote", "cavoli", "melanzana"]
     }
     ]
     },
     {"name": "Dolci",
     "pietanze": [
     {
     "name": "Tiramisù",
     "prezzo": 4,
     "prodotti": ["savoiardi", "caffè", "cacao in polvere", "mascarpone"]
     },
     {
     "name": "Crostata ai frutti di bosco",
     "prezzo": 5,
     "prodotti": ["pastafrolla", "mirtilli", "lamponi", "more", "crema"]
     }
     ]
     }
     ]*/

    $scope.nomeMenu = "";
    $scope.chef = "";

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


    var uncheckAll = function(){
        for(var i = 0, len = $scope.selected.length; i < len; i++) {
            document.getElementById($scope.selected[i].Name).checked = false;
        }
        $scope.selected = [];
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
        $scope.filtro = "";
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
        /*implementare passaggio dati al server*/
    };
});