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

/**
 * Created by alberto on 08/06/17.
 */

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


    @GetMapping
    public Iterable<Pietanza> populateMenu(){
       /* Menu menu1 = new Menu("Menu estivo", "Sapori delicati");
        Menu menu2 = new Menu("Menu invernale", "Sapori non delicati");
        Categoria categoria1 = new Categoria("a");
        Categoria categoria2 = new Categoria("b");
        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);
        menu1.addCategoria(categoria1);
        menu1.addCategoria(categoria2);
        menuRepository.save(menu1);
        menuRepository.save(menu2);*/

        Etichetta etichetta = new Etichetta("piccante");
        //etichettaRepository.save(etichetta);

        Pietanza pietanza = new Pietanza("bruschetta");
        pietanza.addEtichetta(etichetta);
        pietanzaRepository.save(pietanza);
        //System.err.println("Dopo save pietanza         gggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
        //etichettaRepository.save(etichetta);

        return pietanzaRepository.findAll();
    }
}
