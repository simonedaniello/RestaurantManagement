
myApp.service("PrendiComandaService", ["$http", function ($http) {

    this.parseTagArray = function (array) {
        var tagArray = [];
        for (i in array){
            tagArray.push(array[i].classificatore);
        }
        return tagArray;
    };

    this.getTavoli = function (url) {
        return $http.get(url);
    };

}]);
