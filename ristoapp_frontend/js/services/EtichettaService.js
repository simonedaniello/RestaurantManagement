myApp.service('EtichettaService', ['ajaxService', function(ajaxService) {

    this.parseResponse = function(resp) {
        var array = [];
        for (i in resp){
            array.push(resp[i].classificatore);
        }
        return array;
    };

    this.getTag = function() {
        return ajaxService.getResource("http://localhost:8080/tags", null);
    };

    this.postTag = function(nomeTag){
        var dtoTag = {classificatore: nomeTag};
        var jsonTag = JSON.stringify(dtoTag);
        return ajaxService.sendResource("http://localhost:8080/tags", jsonTag);
    };

    this.deleteTag = function(nomeTag){
        return ajaxService.getResource("http://localhost:8080/tags/delete/" + nomeTag.toString(), null);
    };

    this.putTag = function(nomeTagVecchio, nomeTagNuovo){
        var dtoTag = {classificatore: nomeTag};
        var jsonTag = JSON.stringify(dtoTag);
        ajaxService.sendResource("http://localhost:8080/tags", jsonTag);
    };

}]);
