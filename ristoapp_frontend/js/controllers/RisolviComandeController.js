
myApp.controller("RisolviComandeController", function ($scope,ajaxService) {

    $scope.currentItems = [];
    $scope.selectedProdForChef = [];
    $scope.tavolo = null;

    function subscribe() {
        var socket = new SockJS('http://localhost:8080/websocket');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/comande', function (response) {
                var singleComanda = {comandaItems: [], tavolo: 0};
                var comande = response.body;
                var parsed = JSON.parse(comande);
                for (var i in parsed.comandaItems){
                    singleComanda.comandaItems.push(parsed.comandaItems[i]);
                }
                singleComanda.tavolo = parsed.tavolo;
                $scope.currentItems.push(singleComanda);
                $scope.selectedProdForChef = $scope.currentItems;
                $scope.$apply();
            });
        });
    }

    subscribe();


    $scope.fatto = function(indx){
        $scope.selectedProdForChef.splice(indx,1);
    };


});
