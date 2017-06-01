angular.module("ristoApp").controller("headerController", function ($scope, $cookies, ajaxService) {

    $scope.loghide = true;
    $scope.reghide = true;

    $scope.registerClick = function () {
        $scope.loghide = true;
        $scope.reghide = !$scope.reghide;
    };

    $scope.loginClick = function () {
        $scope.reghide = true;
        $scope.loghide = !$scope.loghide;
    };

    $scope.registerButton = function () {
        var e = document.getElementById("typeChoicer");
        var typec = e.options[e.selectedIndex].text;
        var data = {name: $scope.name,
        email: $scope.email, username: $scope.username,
        password: $scope.password, type: typec};
        ajaxService.sendResource("http://localhost:8080/register/add", data).then(
            function (response) {
                $cookies.putObject("credentials", data);
            }
        , function (response) {
                alert(response);
            });
    };
});