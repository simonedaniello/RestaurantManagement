
myApp.controller("creaCategoriaCtrl", function($scope, ajaxService) {

    var init = function () {
        $scope.records = [];
        $scope.selected = [];
        $scope.names = [];
        var param = null;
        ajaxService.getResource("dishes", null).then(function (data) {
            $scope.records = data;
            for(i = 1; i <= $scope.records.length; i++){
                $scope.names.push($scope.records[i].nome) ;
            }
            $scope.names.sort();
        }, function (err) {
            console.log("Err: " + err);
        });
    };

    init();
    $scope.updateSelected = function(idPietanza) {
        var checkBox = document.getElementById(idPietanza);
        var name = idPietanza;
        if (checkBox.checked) {
            $scope.selected.push(name);
            $scope.selected.sort();
        } else {
            $scope.selected.splice($scope.selected.indexOf(name), 1);
        }
    }
});
