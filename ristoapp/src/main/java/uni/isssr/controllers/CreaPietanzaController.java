package uni.isssr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.*;

import java.util.ArrayList;

/**
 * Created by alberto on 09/06/17.
 */

@RestController
@CrossOrigin
@RequestMapping(path = "/creaPietanza")
public class CreaPietanzaController {

    @Autowired
    private EtichettaRepository etichettaRepository;

    @Autowired
    private PietanzaRepository pietanzaRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/addTag")
    public void addTag(@RequestBody Etichetta etichetta){
        etichettaRepository.save(etichetta);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/addDish")
    public void addTag(@RequestBody Pietanza pietanza){
        pietanzaRepository.save(pietanza);
    }

    @GetMapping(path = "/getTags")
    public @ResponseBody Iterable<Etichetta> getAllTags(){
        // Inserimenti per test
        Etichetta e1 = new Etichetta();
        Etichetta e2 = new Etichetta();
        Etichetta e3 = new Etichetta();
        Etichetta e4 = new Etichetta();
        Etichetta e5 = new Etichetta();
        Etichetta e6 = new Etichetta();
        Pietanza p1 = new Pietanza();
        p1.setNome("aaaa");
        ArrayList<Pietanza> list = new ArrayList<>();
        list.add(p1);
        //e1.setPietanza(list);
        e1.setClassificatore("Piccante");
        e2.setClassificatore("Vegetariano");
        e3.setClassificatore("Carne");
        e4.setClassificatore("Pesce");
        e5.setClassificatore("Cristiano");
        e6.setClassificatore("Vegano");
        //
        etichettaRepository.save(e1);
        etichettaRepository.save(e2);
        etichettaRepository.save(e3);
        etichettaRepository.save(e4);
        etichettaRepository.save(e5);
        etichettaRepository.save(e6);
        return etichettaRepository.findAll();
    }

    /*
    @GetMapping(path = "/getProdotti")
    public @ResponseBody Iterable<Prodotto> getAllProdotti() {
    }
    */
}