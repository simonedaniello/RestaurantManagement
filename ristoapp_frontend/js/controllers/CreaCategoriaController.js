
myApp.controller("creaCategoriaCtrl", function($scope) {
    $scope.records = [
        {Name:"Carbonara",                   Category:"Pasta",       Chef:"Cracco" },
        {Name:"Amatriciana",                 Category:"Pasta",       Chef:"Cracco" },
        {Name:"Pasta con lo scoglio",        Category:"Pasta",       Chef:"Cracco" },
        {Name:"Spaghetti agli scampi",       Category:"Pasta",       Chef:"Cracco" },
        {Name:"Lasagne",                     Category:"Primo",       Chef:"Cracco" },
        {Name:"Krapfen",                     Category:"Dolce",       Chef:"Cracco" },
        {Name:"Purea di patate",             Category:"Secondo",     Chef:"Cracco" },
        {Name:"Banana Split",                Category:"Dolce",       Chef:"Cracco" },
        {Name:"Bollito di asparagi",         Category:"Secondo",     Chef:"Cracco" },
        {Name:"Spremuta di arance",          Category:"Bevanda",     Chef:"Cracco" },
        {Name:"Biscotti alla Carlo",         Category:"Dolce",       Chef:"Cracco" },
        {Name:"Pasta al tonno",              Category:"Primo",       Chef:"Cracco" },
        {Name:"Linguine con lumache",        Category:"Primo",       Chef:"Cracco" },
        {Name:"Spaghetti di soia",           Category:"Primo",       Chef:"Cracco" },
        {Name:"Pollo alla romana",           Category:"Secondo",     Chef:"Cracco" }
    ];
    $scope.selected = [];
    $scope.records.sort();
    $scope.updateSelected = function(idPietanza){
        var checkBox = document.getElementById(idPietanza);
        var name = idPietanza;
        if(checkBox.checked) {
            $scope.selected.push(name);
            $scope.selected.sort();
        }else{
            $scope.selected.splice($scope.selected.indexOf(name),1);
        }
    }
});