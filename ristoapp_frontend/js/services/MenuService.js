
myApp.service('MenuService', [function() {

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
                        width: 460
                    }]
                };
                pdfMake.createPdf(docDefinition).download(nomeMenu + ".pdf");
            }
        });
    };

}]);