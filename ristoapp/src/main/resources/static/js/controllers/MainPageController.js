/**
 * Created by alberto on 09/07/17.
 */

myApp.controller("mainPageController", function ($scope, $rootScope, $localStorage, typeService) {

    $scope.adminFunctionHide = true;
    $scope.cuocoFunctionHide = true;
    $scope.cassiereFunctionHide = true;
    $scope.cameriereFunctionHide = true;
    $scope.logged = false;


    var updateType = function (type) {
        $scope.logged = typeService.getLogged();
        if (type === "cassiere") {
            $scope.cassiereFunctionHide = false;
        } else if (type === "cuoco") {
            $scope.cuocoFunctionHide = false;
        } else if (type === "cameriere") {
            $scope.cameriereFunctionHide = false;
        } else if (type === "admin"){
            $scope.adminFunctionHide = false;
            $scope.cameriereFunctionHide = true;
            $scope.cuocoFunctionHide = false;
            $scope.cassiereFunctionHide = true;
        }
    };

    $rootScope.$on("updateType", function (event) {
        updateType(typeService.getType())
    });

    if ($localStorage.user !== undefined && $localStorage.pass !== undefined && $localStorage.type !== undefined){
        $scope.logged = true;
        updateType($localStorage.type);
    }

});