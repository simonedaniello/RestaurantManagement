/**
 * Created by Antonio on 21/06/2017.
 */

myApp.controller("RisolviComandeController", function ($scope,ajaxService, Pubnub) {

    $scope.currentItems = [];
    $scope.selectedProdForChef = [];
    $scope.tavolo = null;
    // Chiavi di accesso al servizio PubNub
    Pubnub.init({
        publishKey: 'pub-c-18cb2897-1549-41e9-9011-c0c17480a1e4',
        subscribeKey: 'sub-c-23206a28-56ae-11e7-97fe-02ee2ddab7fe'
    });

    $scope.subscribe = function () {
        // Subscribe to a channel
        Pubnub.addListener({
            status: function(statusEvent) {
                if (statusEvent.category === "PNUnknownCategory") {
                    var newState = {new: 'error'};
                    Pubnub.setState({state: newState},
                        function (status) {
                            console.log(status)
                        }
                    );
                }
            },

            // Callback riguardante un evento che è stato appena pubblicato
            message: function(response) {
                var singleComanda = {comandaItems:[],         tavolo:0};
                var comande = response.message;
                /*var parsed = JSON.parse(comande);*/
/*                console.log(parsed);*/
                var parsed = JSON.parse(comande);
                /*console.log((parsed.comandaItems[].nome));*/
                for(var i in parsed.comandaItems){
                    singleComanda.comandaItems.push(parsed.comandaItems[i]);
                }
                singleComanda.tavolo = parsed.tavolo;
                $scope.currentItems.push(singleComanda);

                $scope.selectedProdForChef = $scope.currentItems;

                $scope.$apply();
                //$scope.selectedProdForChef = comande;
                //$scope.$apply();
            }
        });

        Pubnub.subscribe({
            channels: ['channel_comande']
        });
        alert("SUBSCRIBED!");
    };

    $scope.fatto = function(indx){
        console.log("selected prod for chef : " + angular.toJson($scope.selectedProdForChef[indx]));
        ajaxService.sendResource("http://localhost:8080/comanda/addComanda", angular.toJson($scope.selectedProdForChef[indx])).then(function (response) {
            console.log("selected prod for chef : " + angular.toJson($scope.selectedProdForChef[indx]));
            $scope.selectedProdForChef.splice(indx,1);
        }, function (response) {
            alert("There was a problem");
            console.log(response)
        });
    };

});
