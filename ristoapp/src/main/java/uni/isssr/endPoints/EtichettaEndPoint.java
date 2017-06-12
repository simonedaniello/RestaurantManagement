package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.Etichetta;
import uni.isssr.repositories.EtichettaRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/tags")
public class EtichettaEndPoint {

    @Autowired
    private EtichettaRepository etichettaRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void addTag(@RequestBody Etichetta etichetta){
        etichettaRepository.save(etichetta);
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Etichetta> getAllTags(){
        // Inserimenti per test
        Etichetta e1 = new Etichetta();
        Etichetta e2 = new Etichetta();
        Etichetta e3 = new Etichetta();
        Etichetta e4 = new Etichetta();
        Etichetta e5 = new Etichetta();
        Etichetta e6 = new Etichetta();
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
}
