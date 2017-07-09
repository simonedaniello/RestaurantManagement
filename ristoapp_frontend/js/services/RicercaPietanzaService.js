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

    this.generateSearchURL = function (nome, arrayTags) {
        var url = "https://localhost:8080/dish?nome="+nome;
        for (i in arrayTags){
            var temp = "&tags=" + arrayTags[i];
            url += temp;
        }
        return url;
    }

}]);