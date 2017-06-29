myApp.service('EtichettaService', ['ajaxService', function(ajaxService) {

    var tagArray = [];

    var getTagPromise = function () {
        ajaxService.getResource("http://localhost:8080/tags", null).then(
            function (response) {
                for (i in response){
                    tagArray.push(response[i].classificatore);
                }
            }
            , function (response) {
                alert("Couldn't get tags");
            });
    };

    this.getTag = function() {
        getTagPromise();
        return tagArray;
    };

    this.postTag = function(nomeTag){
        var tagArray = [];
        var dtoTag = {classificatore: nomeTag};
        var jsonTag = JSON.stringify(dtoTag);
        ajaxService.sendResource("http://localhost:8080/tags", jsonTag).then(function (response) {
            getTagPromise();
        }, function (response) {
            alert("Couldn't save tag");
        });
        return tagArray;
    };

    this.putTag = function(nomeTagVecchio, nomeTagNuovo){
        var dtoTag = {classificatore: nomeTag};
        var jsonTag = JSON.stringify(dtoTag);
        ajaxService.sendResource("http://localhost:8080/tags", jsonTag).then(function (response) {
            getTagPromise();
        }, function (response) {
            alert("Couldn't save tag");
        });
        return tagArray;
    };

    this.deleteTag = function(nomeTag){
        ajaxService.getResource("http://localhost:8080/tags/delete/" + nomeTag.toString(), null).then(function (response) {
            getTagPromise();
        }, function (response) {
            alert("Couldn't delete tags");
        });
        return tagArray;
    };

}]);
