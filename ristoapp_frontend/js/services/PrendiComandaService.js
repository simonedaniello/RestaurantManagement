
myApp.service("PrendiComandaService", [function () {


    this.parseTagArray = function (array) {
        var tagArray = [];
        for (i in array){
            tagArray.push(array[i].classificatore);
        }
        return tagArray;
    };

}]);
