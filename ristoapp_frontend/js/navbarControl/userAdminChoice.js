/**
 * Created by dandi on 20/06/17.
 */

var button = document.getElementById('useradmin');
document.getElementById('cuoco').checked = true;
utenteadminfunzione();

function utenteadminfunzione(){
    var it;
    if(document.getElementById('cassiere').checked) {
        it = 'Cassiere';
        button.innerText = button.textContent = it;
        document.getElementById('menu-box').style.display = "none";
        document.getElementById('creamenu-box').style.display = "none";
        document.getElementById('creapietanze-box').style.display = "none";
        document.getElementById('lemiepietanze-box').style.display = "none";
        document.getElementById('gestiscitag-box').style.display = "none";
        document.getElementById('listacomandecuoco-box').style.display = "none";
        document.getElementById('ordine-box').style.display = "none";
        document.getElementById('prendicomanda-box').style.display = "none";
        document.getElementById('cassa-box').style.display = "block";


        document.getElementById('menu').style.display = "none";
        document.getElementById('creamenu').style.display = "none";
        document.getElementById('prendicomanda').style.display = "none";
        document.getElementById('listacomandecuoco').style.display = "none";
        document.getElementById('pietanze').style.display = "none";
        document.getElementById('cassa').style.display = "block";
        document.getElementById('gestioneTag').style.display = "none";
        document.getElementById('ordinaMagazzino').style.display = "none";
    }else if(document.getElementById('cuoco').checked) {
        it = 'Cuoco';
        button.innerText = button.textContent = it;
        document.getElementById('menu-box').style.display = "block";
        document.getElementById('creamenu-box').style.display = "block";
        document.getElementById('creapietanze-box').style.display = "block";
        document.getElementById('lemiepietanze-box').style.display = "block";
        document.getElementById('gestiscitag-box').style.display = "block";
        document.getElementById('listacomandecuoco-box').style.display = "block";
        document.getElementById('ordine-box').style.display = "block";
        document.getElementById('prendicomanda-box').style.display = "none";
        document.getElementById('cassa-box').style.display = "none";


        document.getElementById('menu').style.display = "block";
        document.getElementById('creamenu').style.display = "block";
        document.getElementById('prendicomanda').style.display = "none";
        document.getElementById('listacomandecuoco').style.display = "block";
        document.getElementById('pietanze').style.display = "block";
        document.getElementById('cassa').style.display = "none";
        document.getElementById('gestioneTag').style.display = "block";
        document.getElementById('ordinaMagazzino').style.display = "block";
    }else if(document.getElementById('cameriere').checked) {
        it = 'Cameriere';
        button.innerText = button.textContent = it;
        document.getElementById('menu-box').style.display = "none";
        document.getElementById('creamenu-box').style.display = "none";
        document.getElementById('creapietanze-box').style.display = "none";
        document.getElementById('lemiepietanze-box').style.display = "none";
        document.getElementById('gestiscitag-box').style.display = "none";
        document.getElementById('listacomandecuoco-box').style.display = "none";
        document.getElementById('ordine-box').style.display = "none";
        document.getElementById('prendicomanda-box').style.display = "block";
        document.getElementById('cassa-box').style.display = "none";


        document.getElementById('menu').style.display = "none";
        document.getElementById('creamenu').style.display = "none";
        document.getElementById('prendicomanda').style.display = "block";
        document.getElementById('listacomandecuoco').style.display = "none";
        document.getElementById('pietanze').style.display = "none";
        document.getElementById('cassa').style.display = "none";
        document.getElementById('gestioneTag').style.display = "none";
        document.getElementById('ordinaMagazzino').style.display = "none";
    }
}




