myApp.service('EtichettaService', ['ajaxService', function(ajaxService) {

    this.postTag = function(nomeTag){
        var dtoTag = {classificatore: nomeTag};
        var jsonTag = JSON.stringify(dtoTag);
        ajaxService.sendResource("http://localhost:8080/tags", jsonTag).then(function (response) {
        }, function (response) {
            alert("Couldn't save tag");
        });
    };

    this.putTag = function(nomeTagVecchio, nomeTagNuovo){
        var dtoTag = {classificatore: nomeTag};
        var jsonTag = JSON.stringify(dtoTag);
        ajaxService.sendResource("http://localhost:8080/tags", jsonTag).then(function (response) {
        }, function (response) {
            alert("Couldn't save tag");
        });
    };

    this.deleteTag = function(nomeTag){
        ajaxService.getResource("http://localhost:8080/tags/delete/" + nomeTag.toString(), null).then(function (response) {
            console.log("elimino");
        }, function (response) {
            alert("Couldn't delete tags");
        });
    };

    this.getTag = function() {
        var tagArray = [];
        ajaxService.getResource("http://localhost:8080/tags", null).then(
            function (response) {
                for (i in response){
                    tagArray.push(response[i].classificatore);
                    console.log(response[i].classificatore);
                }
                console.log("prendo i tag");
            }
            , function (response) {
                alert("Couldn't get tags");
            });
        return tagArray;
    };

}]);
