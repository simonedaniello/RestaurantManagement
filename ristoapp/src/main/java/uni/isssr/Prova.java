package uni.isssr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isssr.entities.*;
import uni.isssr.repositories.CategoriaRepository;
import uni.isssr.repositories.MenuRepository;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.repositories.ProdottoRepository;

import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path = "/generate")
public class Prova {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PietanzaRepository pietanzaRepository;


    /*

    Popola un menù e lo restituisce con tutti gli elementi strutturati all'interno.
    Viene fatta solo la save del menu perchè nelle aggregazioni c'è il cascade ALL che propaga
    l'inserimento per tutti gli oggetti linkati da menu e così ricorsivamente. L'add dell'etichetta
    ad una pietanza viene fatto dalla pietanza stessa che rappresenta l'owning side della many to many
    bidirezionale ma è possibile farlo dalla parte dell'etichetta per maggior consistenza.

     */


    @GetMapping
    public Iterable<Menu> populateMenu(){

        Prodotto pd1 = new Prodotto(1L, "cozze");
        Prodotto pd2 = new Prodotto(2L, "vongole");
        Prodotto pd3 = new Prodotto(3L, "riso");

        prodottoRepository.save(pd1);
        prodottoRepository.save(pd2);
        prodottoRepository.save(pd3);

        Ingrediente i1 = new Ingrediente(pd1, 10);
        Ingrediente i2 = new Ingrediente(pd2, 20);
        Ingrediente i3 = new Ingrediente(pd3, 100);

        List<Ingrediente> ingredienti = Arrays.asList(i1, i2, i3);

        Etichetta e1 = new Etichetta("piccante");
        Etichetta e2 = new Etichetta("celiaco");
        //Etichetta e3 = new Etichetta("vegano");

        Pietanza p1 = new Pietanza("risotto alla pescatora", 10.00);
        //Pietanza p2 = new Pietanza("pasta alla carbonara", 12.50);
        //Pietanza p3 = new Pietanza("verdure grigliate", 8.00);

        p1.addEtichetta(e1);
        p1.addEtichetta(e2);
        p1.setIngredienti(ingredienti);

        Categoria categoria1 = new Categoria("Primi");
        //Categoria categoria2 = new Categoria("Secondi");

        categoria1.addPietanza(p1);

        Menu menu1 = new Menu("Menu estivo", "Sapori delicati");

        menu1.addCategoria(categoria1);

        menuRepository.save(menu1);

        return menuRepository.findAll();
    }
}

