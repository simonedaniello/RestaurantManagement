/**
 * Created by dandi on 20/06/17.
 */

var button = document.getElementById('useradmin');
document.getElementById('cuoco').checked = true;
utenteadminfunzione();

function utenteadminfunzione(){
    if(document.getElementById('utente').checked) {
        it = 'Utente';
        button.innerText = button.textContent = it;
        document.getElementById('menu').style.display = "none";
        document.getElementById('creamenu').style.display = "none";
        document.getElementById('prendicomanda').style.display = "none";
        document.getElementById('listacomandecuoco').style.display = "none";
        document.getElementById('pietanze').style.display = "none";
        document.getElementById('mostramenu').style.display = "block";
        document.getElementById('contattaci').style.display = "block";
        document.getElementById('dovesiamo').style.display = "block";
        document.getElementById('cassa').style.display = "none";
    }else if(document.getElementById('cuoco').checked) {
        it = 'Cuoco';
        button.innerText = button.textContent = it;
        document.getElementById('menu').style.display = "block";
        document.getElementById('creamenu').style.display = "block";
        document.getElementById('prendicomanda').style.display = "none";
        document.getElementById('listacomandecuoco').style.display = "block";
        document.getElementById('pietanze').style.display = "block";
        document.getElementById('mostramenu').style.display = "none";
        document.getElementById('contattaci').style.display = "none";
        document.getElementById('dovesiamo').style.display = "none";
        document.getElementById('cassa').style.display = "none";
    }else if(document.getElementById('cameriere').checked) {
        it = 'Cameriere';
        button.innerText = button.textContent = it;
        document.getElementById('menu').style.display = "none";
        document.getElementById('creamenu').style.display = "none";
        document.getElementById('prendicomanda').style.display = "block";
        document.getElementById('listacomandecuoco').style.display = "none";
        document.getElementById('pietanze').style.display = "none";
        document.getElementById('mostramenu').style.display = "none";
        document.getElementById('contattaci').style.display = "none";
        document.getElementById('dovesiamo').style.display = "none";
        document.getElementById('cassa').style.display = "block";
    }
}




