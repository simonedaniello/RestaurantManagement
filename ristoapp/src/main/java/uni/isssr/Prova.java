package uni.isssr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isssr.entities.*;
import uni.isssr.repositories.CategoriaRepository;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.MenuRepository;
import uni.isssr.repositories.PietanzaRepository;


@RestController
@CrossOrigin
@RequestMapping(path = "/generate")
public class Prova {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EtichettaRepository etichettaRepository;

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

        Ingrediente i1 = new Ingrediente("cozze", 10);
        Ingrediente i2 = new Ingrediente("vongole", 20);
        Ingrediente i3 = new Ingrediente("riso", 100);

        Etichetta e1 = new Etichetta("piccante");
        Etichetta e2 = new Etichetta("celiaco");
        Etichetta e3 = new Etichetta("vegano");

        Pietanza p1 = new Pietanza("risotto alla pescatora", 10.00);
        Pietanza p2 = new Pietanza("pasta alla carbonara", 12.50);
        Pietanza p3 = new Pietanza("verdure grigliate", 8.00);

        p1.addIngrediente(i1);
        p1.addIngrediente(i2);
        p1.addIngrediente(i3);

        /*
        Equivalentemente posso modificare il db dal non owning side
        perchè nell'impementazione di addPietanza aggiungo anche dall'owning side

        e1.addPietanza(p1);
        e1.addPietanza(p2);
        e2.addPietanza(p2);
        e3.addPietanza(p3);

        */

        p1.addEtichetta(e1);
        p2.addEtichetta(e1);
        p2.addEtichetta(e2);
        p3.addEtichetta(e3);

        Categoria categoria1 = new Categoria("Primi");
        Categoria categoria2 = new Categoria("Secondi");

        categoria1.addPietanza(p1);
        categoria1.addPietanza(p2);
        categoria2.addPietanza(p3);

        Menu menu1 = new Menu("Menu estivo", "Sapori delicati");

        menu1.addCategoria(categoria1);
        menu1.addCategoria(categoria2);

        menuRepository.save(menu1);

        return menuRepository.findAll();
    }
}
