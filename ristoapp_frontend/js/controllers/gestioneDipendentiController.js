/**
 * Created by Simone on 13/07/2017.
 */

myApp.controller("GestioneDipendentiController", function($scope, ajaxService) {

    $scope.nomeDipendente = "";
    $scope.cognomeDipendente = "";
    $scope.ruoloDipendente = "";

    var parseResponse = function(resp) {
        var array = [];
        for (var i in resp){
            array.push(resp[i]);
        }
        return array;
    };

    var updateDipendenti = function() {
        ajaxService.getResource("https://localhost:8080/dipendenti", null).then(
            function (response) {
                $scope.dipendenti = parseResponse(response);
            }
            , function (response) {
                alert("Non sono riuscito a caricare i dati relativi ai dipendenti");
            });
    };
    updateDipendenti();

    $scope.saveDipendente = function(){
        if ($scope.nomeDipendente === "") {
            alert("Specificare il nome del dipendente da aggiungere.");
            return;
        }
        var dtoDipendente = {nome: $scope.nomeDipendente, cognome: $scope.cognomeDipendente, ruolo: $scope.ruoloDipendente};
        var jsonDipendente = JSON.stringify(dtoDipendente);
        ajaxService.sendResource("https://localhost:8080/dipendenti", jsonDipendente).then(
            function (response) {
                updateDipendenti();
            }
            ,function (response) {
                alert("Non sono riuscito a salvare il dipendente");
            });
        $scope.nomeDipendente = "";
        $scope.cognomeDipendente = "";
        $scope.ruoloDipendente = "";
    };

    $scope.deleteDipendente = function(nome){
        ajaxService.deleteResource("https://localhost:8080/dipendenti/" + nome.toString(), null).then(
            function (response) {
                updateDipendenti();
            }
            ,function (response) {
                alert("Non sono riuscito ad eliminare il dipendente");
            });
    };

    $scope.modificaDipendente = function(dipendente){
        var ruolodipendente = document.getElementById("ruolo." + dipendente.cognome).value;
        if (ruolodipendente.value === "") {
            alert("Specificare un nome non vuoto per la modifica.");
            return;
        }
        var dtoDipendente = {nome: dipendente.nome, cognome: dipendente.cognome, ruolo: ruolodipendente};
        var jsonDipendente = JSON.stringify(dtoDipendente);
        console.log("Stringa = " + jsonDipendente);
        ajaxService.updateResource("https://localhost:8080/dipendenti/" + dipendente.cognome.toString(), jsonDipendente).then(
            function (response) {
                updateDipendenti();
            }
            ,function (response) {
                alert("Non sono riuscito a modificare il ruolo del dipendente");
            });
    };
});