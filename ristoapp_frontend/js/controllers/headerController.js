myApp.controller("headerController", function ($scope, $localStorage, ajaxService) {

    $scope.loghide = true;
    $scope.reghide = true;

    $scope.$storage = $localStorage;


    $scope.registerClick = function () {
        $scope.loghide = true;
        $scope.reghide = !$scope.reghide;
    };

    $scope.loginClick = function () {
        $scope.reghide = true;
        $scope.loghide = !$scope.loghide;
    };

    $scope.loginButton = function () {
        var nome = $scope.loginNome;
        var pass = $scope.loginPass;
        var data = {name: nome, password: pass};
        ajaxService.getResource("http://localhost:8080/login", data).then(
            function (response) {
                console.log("loggato: " + nome);
            }
            , function (response) {
                alert("Username o Password non corretti");
            });
    };

    $scope.registerButton = function () {
        var e = document.getElementById("typeChoicer");
        var typec = e.options[e.selectedIndex].text;
        var data = {name: $scope.name,
        email: $scope.email, username: $scope.username,
        password: $scope.password, type: typec};
        ajaxService.sendResource("http://localhost:8080/register/add", data).then(
            function (response) {
                $localStorage.data = data;
            }
        , function (response) {
                alert(response);
            });
    };
});