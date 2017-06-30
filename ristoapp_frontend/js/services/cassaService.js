myApp.service('cassaService', ['ajaxService', function(ajaxService) {

    this.parseResponse = function(resp) {
        var array = [];
        for (x in resp){
            array.push(resp[x]);
        }
        return array;
    };

    this.getConsumazioni = function() {
        return ajaxService.getResource("http://localhost:8080/xxx", null);
    };

    this.postConsumazioni = function(){
        var dtoTag = {status: "OK"};
        var jsonTag = JSON.stringify(dtoTag);
        return ajaxService.sendResource("http://localhost:8080/xxx", jsonTag);
    };

}]);
