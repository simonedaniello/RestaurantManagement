package uni.isssr.endPoints;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.Comanda;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.ComandaRepository;
import uni.isssr.repositories.PietanzaRepository;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/comanda")
public class PrendiComandaEndPoint {

    private final Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ComandaRepository comandaRepository;


    @RequestMapping(method = RequestMethod.POST)
    public void addComanda(@RequestBody List<Comanda> listaComande){

        log.info(listaComande.get(0).getNome());
        for (Comanda aListaComande : listaComande) {
            comandaRepository.save(aListaComande);
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Comanda> getAllComande() {
        System.out.println("----------- comande : " + comandaRepository.findAll());
        Iterable<Comanda> c =  comandaRepository.findAll();
        comandaRepository.deleteAll();
        return c;
    }

}