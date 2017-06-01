angular.module("ristoApp", ["ngCookies"]).controller("pageController", function ($scope, $cookies) {

    if ($cookies.getObject("credentials") != null){
        alert("COOKIE FOUND!!!");
        $cookies.remove("credentials");
    }
});