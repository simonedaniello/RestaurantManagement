
myApp.controller("RisolviComandeController", function ($scope,ajaxService) {

    $scope.currentItems = [];
    $scope.selectedProdForChef = [];
    $scope.tavolo = null;

    function subscribe() {
        var socket = new SockJS('https://localhost:8080/websocket');
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
        console.log($scope.selectedProdForChef[indx]);
        for (var j=0;  j<$scope.selectedProdForChef[indx].comandaItems.length; j++) {
            var pietanzaResocontoDto = {value: $scope.selectedProdForChef[indx].comandaItems[j].quantita,
                prodottoId: $scope.selectedProdForChef[indx].comandaItems[j].id};
            var json = JSON.stringify(pietanzaResocontoDto);
            console.log(pietanzaResocontoDto);
            ajaxService.sendResource("https://localhost:8080/resoconto/preparato", json).then(
                function (response) {
                }
                ,function (response) {
                    alert("Couldn't send report");
                });
        }
        $scope.selectedProdForChef.splice(indx,1);
    };


});
