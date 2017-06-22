/**
 * Created by Antonio on 21/06/2017.
 */

myApp.controller("RisolviComandeController", function ($scope, Pubnub) {

    $scope.currentItems = [];
    $scope.selectedProdForChef = [];
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
                var comande = response.message;
                /*var parsed = JSON.parse(comande);*/
/*                console.log(parsed);*/
                var parsed = JSON.parse(comande);
                for(var i in parsed){
                    /*console.log("prodotto = "  + parsed[i].nome);*/
                    $scope.currentItems.push(parsed[i]);
                }

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

    $scope.itemDone = function(){

    }

});
