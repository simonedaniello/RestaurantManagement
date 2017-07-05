myApp.service("ajaxService", function ($q, $http) {

    var ajax = function(method,url,data) {

        var deferred = $q.defer();
        var request = {
            method: method,
            url: url,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        if (method === 'GET') {
            request.params = data;
        } else {
            request.data = data;
        }
        $http(request).then(function(response){
            deferred.resolve(response.data);
        },function(response, status){
            deferred.reject({data: response, status: status});
        });
        return deferred.promise;
    };

    this.sendResource = function (url, params) {
        return ajax("POST", url, params);
    };

    this.getResource = function (url, params) {
        return ajax("GET", url, params);
    };

    this.deleteResource = function (url, params) {
        return ajax("DELETE", url, params);
    };

    this.updateResource = function (url, params) {
        return ajax("PUT", url, params);
    };

});
