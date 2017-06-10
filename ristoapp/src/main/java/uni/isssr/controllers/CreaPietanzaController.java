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

    private boolean first = true;

    @Autowired
    private EtichettaRepository etichettaRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/addTag")
    public void addTag(@RequestBody Etichetta etichetta){
        etichettaRepository.save(etichetta);
    }

    @GetMapping(path = "/getTags")
    public @ResponseBody Iterable<Etichetta> getAllTags(){
        // Inserimenti per test
        Etichetta e1 = new Etichetta();
        Etichetta e2 = new Etichetta();
        Pietanza p1 = new Pietanza();
        p1.setNome("aaaa");
        ArrayList<Pietanza> list = new ArrayList<>();
        list.add(p1);
        e1.setPietanza(list);
        e1.setClassificatore("Piccante");
        e2.setClassificatore("Vegetariano");
        //
        etichettaRepository.save(e1);
        etichettaRepository.save(e2);
        return etichettaRepository.findAll();
    }

    @GetMapping(path = "/getProdotti")
    public @ResponseBody Iterable<Prodotto> getAllProdotti(){
        // Inserimenti per test
        if (first){
            first = false;
            Prodotto p1 = new Prodotto();
            Prodotto p2 = new Prodotto();
            Prodotto p3 = new Prodotto();
            Prodotto p4 = new Prodotto();
            Prodotto p5 = new Prodotto();
            Prodotto p6 = new Prodotto();
            Prodotto p7 = new Prodotto();
            Prodotto p8 = new Prodotto();
            p1.setNome("uova");
            p2.setNome("sale");
            p3.setNome("olio");
            p4.setNome("pasta");
            p5.setNome("aceto");
            p6.setNome("zucchina");
            p7.setNome("prosciutto crudo");
            p8.setNome("pomodoro");
            prodottoRepository.save(p1);
            prodottoRepository.save(p2);
            prodottoRepository.save(p3);
            prodottoRepository.save(p4);
            prodottoRepository.save(p5);
            prodottoRepository.save(p6);
            prodottoRepository.save(p7);
            prodottoRepository.save(p8);
        }
        //
        return prodottoRepository.findAll();
    }
}