/**
 * Created by alberto on 20/06/17.
 */


myApp.service("RicercaPietanzaService", [function () {


    this.parseTagArray = function (array) {
        var tagArray = [];
        for (i in array){
            tagArray.push(array[i].classificatore);
        }
        return tagArray;
    };

}]);