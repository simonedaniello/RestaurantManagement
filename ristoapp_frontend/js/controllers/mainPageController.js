myApp.controller("mainPageController", function($scope, $location) {


    $scope.redirectTo = function(path){
        $location.path(path);
    }
});