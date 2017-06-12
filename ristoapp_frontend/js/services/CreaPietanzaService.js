/**
 * Created by alberto on 09/06/17.
 */

myApp.service("CreaPietanzaService", [function () {


    this.parseTagArray = function (array) {
        var tagArray = [];
        for (i in array){
            tagArray.push(array[i].classificatore);
        }
        return tagArray;
    };

    this.parseProductList = function (array) {
        var productArray = [];
        for (i in array){
            productArray.push(array[i].nome);
        }
        return productArray;
    };

}]);
