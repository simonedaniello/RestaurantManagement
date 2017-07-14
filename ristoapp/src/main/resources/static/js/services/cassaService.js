myApp.service('CassaService', ['$http', function($http) {

    this.getComanda = function(url) {
        return $http.get(url);
    };

    this.updateComanda = function (url) {
        return $http.put(url);
    };

    this.calcolaTotale = function (comandaItems) {
        var prezzoTotale = 0;
        for (var i = 0; i < comandaItems.length; i++) {
            var comandaItem = comandaItems[i];
            prezzoTotale += comandaItem.prezzo * comandaItem.quantita;
        }
        return prezzoTotale;
    };

}]);
