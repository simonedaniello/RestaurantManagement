
myApp.controller("pageController", function ($scope, $cookies) {
    if ($cookies.getObject("credentials") != null){
        alert("COOKIE FOUND!!!");
        $cookies.remove("credentials");
    }
});


myApp.config(function($routeProvider) {
    $routeProvider
        .when("/monday", {
            templateUrl : "htmlFiles/componiCategoria.html"
        })
        .when("/table", {
            templateUrl : "htmlFiles/ricercaMenu.html"
            /*controller:"table_controller"*/
        })
        .when("/creamenu", {
            templateUrl : "htmlFiles/creaMenu.html"
        })
        .otherwise({
            templateUrl : "htmlFiles/mainPage.html"
        });
});


myApp.controller('ajaxController', function($scope, $http) {

    /* var recipe = $scope.namerecipe;
     var itemInList = $scope.itemInList.Country;*/

    $http.get("list.txt").then(function (response) {
        $scope.myData = response.data.records;
    });

    $scope.researchInText = function() {
        return /*itemInList.isEqual(recipe);*/ true;
    }
});

function checkCountry(country1, country2) {
    return country1 === country2;
}

myApp.controller('researchController', function($scope) {
    $scope.researchInText = function(tag, written) {
        return tag.contains(written);
    }
});


myApp.controller('table_controller', function($scope) {
    $scope.ordered_columns = [];

    $scope.all_columns = [{
        "title": "name",
        "type": "string",
        "checked": true
    }, {
        "title": "age",
        "type": "number",
        "checked": true
    }, {
        "title": "city",
        "type": "string",
        "checked": true

    }, {
        "title": "state",
        "type": "string",
        "checked": false
    }, {
        "title": "job",
        "type": "string",
        "checked": false
    }];

    // i.e. the rows
    $scope.data = [{
        "name": "aleck",
        "age": 33,
        "city": "Portland",
        "state": "OR",
        "job": "developer"
    }, {
        "name": "john",
        "age": 40,
        "city": "Portland",
        "state": "OR",
        "job": "designer"
    }, {
        "name": "erik",
        "age": 34,
        "city": "Portland",
        "state": "OR",
        "job": "sales"
    }];

    $scope.$watch('all_columns', function() {
        update_columns();
    }, true);

    var update_columns = function() {
        $scope.ordered_columns = [];
        for (var i = 0; i < $scope.all_columns.length; i++) {
            var column = $scope.all_columns[i];
            if (column.checked) {
                $scope.ordered_columns.push(column);
            }
        }
    };
});

myApp.controller('repeatController', function($scope) {

    $scope.x = "sono stato chiamato";

    $scope.Myclick = function(category){
        var table = document.getElementById("choosenTable");
        var row = table.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        cell1.innerHTML = category.Name;
        cell2.innerHTML = category.Category;
        cell3.innerHTML = category.Chef;
        console.log("change detected " + category.Category);
        $scope.x = "sono stato cliccato";
    };

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
    ]
});



