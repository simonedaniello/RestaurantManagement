

myApp.controller("creaMenuCtrl", function($scope) {

    $scope.categorie = [
        {name:"Primo",
            pietanze:[{name:"pasta carbonara", prodotti:[{name:"spaghetti"}, {name:"uova"}, {name:"pancetta"}]},
                {name:"riso pomodoro", prodotti:[{name:"riso"}, {name:"pomodoro"}, {name:"olio"}]},
                {name:"pasta allo scoglio", prodotti:[{name:"pasta"}, {name:"cozze"}, {name:"seppie"}]}]},
        {name:"Secondo",
            pietanze:[{name:"carne", prodotti:[{name:"porco"}, {name:"mucca"}, {name:"toro"}]},
                {name:"pesce", prodotti:[{name:"squalo"}, {name:"alghe"}, {name:"medusa"}]},
                {name:"mix", prodotti:[{name:"cavallo"}, {name:"maiale"}, {name:"squalo"}]}]}
    ];
});