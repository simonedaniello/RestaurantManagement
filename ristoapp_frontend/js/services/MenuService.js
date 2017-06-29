
myApp.service('MenuService', ["$http", function($http) {

    this.getMenu = function(url) {
        return $http.get(url);
    };

    this.getPdf = function (div, nomeMenu) {
        html2canvas(div, {
            onrendered: function(canvas) {
                var myImage = canvas.toDataURL("image/png");
                var ctx = canvas.getContext('2d');
                ctx.webkitImageSmoothingEnabled = false;
                ctx.mozImageSmoothingEnabled = false;
                ctx.imageSmoothingEnabled = false;
                var docDefinition = {
                    content: [{
                        image: myImage,
                        width: 450
                    }]
                };
                pdfMake.createPdf(docDefinition).download(nomeMenu + ".pdf");
            }
        });
    };

}]);