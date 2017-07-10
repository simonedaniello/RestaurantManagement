/**
 * Created by alberto on 09/07/17.
 */

// Service per condividere dati tra headerController e MainPageController
myApp.service("typeService", function () {

    this.logged = null;
    this.type = null;

    this.setType = function (ntype) {
        this.type = ntype;
    };

    this.getType = function () {
        return this.type;
    };

    this.setLogged = function (nlogged) {
        this.logged = nlogged;
    };

    this.getLogged = function () {
        return this.logged;
    };

});