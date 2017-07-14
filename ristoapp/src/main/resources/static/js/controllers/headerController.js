myApp.controller("headerController", function ($scope, $rootScope, $localStorage, $route, $location, ajaxService, typeService) {

    $scope.loghideContent = true;
    $scope.reghideContent = true;
    $scope.adminFunctionHide = true;
    $scope.cuocoFunctionHide = true;
    $scope.cassiereFunctionHide = true;
    $scope.cameriereFunctionHide = true;
    $scope.logged = false;

    $scope.$storage = $localStorage;

    var updateType = function (type) {
        typeService.setType(type);
        typeService.setLogged($scope.logged);
        $rootScope.$emit("updateType");
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


    $scope.registerClick = function () {
        $scope.reghideContent = !$scope.reghideContent;
    };

    $scope.loginClick = function () {
        $scope.loghideContent = !$scope.loghideContent;
    };

    $scope.loginButton = function () {
        var nome = $scope.loginNome;
        var pass = $scope.loginPass;
        if ($scope.loginForm.$invalid) {
            alert("ERRORE: alcuni parametri di login mancanti");
            return;
        }
        var data = {name: nome, password: pass};
        ajaxService.getResource("https://localhost:8080/login", data).then(
            function (response) {
                alert("Login eseguito correttamente");
                $scope.loghideContent = true;
                $scope.logged = true;
                updateType(response.type);
                $localStorage.user = $scope.loginNome;
                $localStorage.pass = $scope.loginPass;
                $localStorage.type = response.type;
            }
            , function (response) {
                alert(response.data.data.message);
            });
    };

    $scope.logOutClick = function () {
        $location.path("/");
        $localStorage.$reset();
        $scope.loghideContent = true;
        $scope.reghideContent = true;
        $scope.adminFunctionHide = true;
        $scope.cuocoFunctionHide = true;
        $scope.cassiereFunctionHide = true;
        $scope.cameriereFunctionHide = true;
        $scope.logged = false;
        $route.reload();
        alert("Logout eseguito");
    };

    $scope.registerButton = function () {
        if ($scope.registerForm.$invalid) {
            alert("ERRORE: alcuni parametri di registrazione mancanti");
            return;
        }
        if ($scope.password !== $scope.confirmPassword) {
            alert("ERRORE: password non coincidenti");
            return;
        }
        var e = document.getElementById("typeChoicer");
        var typec = e.options[e.selectedIndex].text;
        var data = {
            name: $scope.name,
            email: $scope.email, username: $scope.username,
            password: $scope.password, type: typec
        };
        console.log($scope.registerForm);
        ajaxService.sendResource("https://localhost:8080/register/add", data).then(
            function (response) {
                alert("Utente inserito correttamente nel sistema");
            }
            , function (response) {
                alert(response.data.data.message);
            });
    };

    if ($localStorage.user !== undefined && $localStorage.pass !== undefined && $localStorage.type !== undefined){
        $scope.loginNome = $localStorage.user;
        $scope.logged = true;
        updateType($localStorage.type);
    }

});