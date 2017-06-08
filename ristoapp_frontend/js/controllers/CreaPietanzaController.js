myApp.controller("CreaPietanzaController", function($scope) {
    $scope.prodotti = [
        {name:"uovo",                   id:1},
        {name:"sale",                   id:2},
        {name:"olio",                   id:3},
        {name:"pasta",                  id:4},
        {name:"aceto",                  id:5},
        {name:"zucchina",               id:6},
        {name:"prosciutto crudo",       id:7},
        {name:"pomodoro",               id:8}
    ];
    $scope.selectedProd = [];

     var searchIndex = function(searchTerm){
         for(var i = 0, len = $scope.selectedProd.length; i < len; i++) {
             if ($scope.selectedProd[i].name === searchTerm) {
                 return i;
             }
         }
     };

    $scope.updateSelected = function(nomeProd){
        var checkBox = document.getElementById("check.".concat(nomeProd));
        if(checkBox.checked) {
            var ingrediente = {name:nomeProd,       quantita:0};
            $scope.selectedProd.push(ingrediente);
            $scope.selectedProd.sort(function(a, b){
                return (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0)
            });
        }else{
            var i = searchIndex(nomeProd);
            $scope.selectedProd.splice(i,1);
        }
    }
});