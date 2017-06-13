package uni.isssr.endPoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.controllers.LoginController;
import uni.isssr.entities.Ingrediente;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.PietanzaRepository;



@RestController
@RequestMapping(path = "/dish")
public class PietanzaEndPoint {

    @Autowired
    private PietanzaRepository pietanzaRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(method = RequestMethod.POST)
    public void addPietanza(@RequestBody Pietanza received){
        log.info(received.toString());
        log.info(received.getNome());
        //log.info(received.getEtichette().get(0).getClassificatore());
        pietanzaRepository.save(received);
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Pietanza> getAllPietanze() {
        return pietanzaRepository.findAll();
    }

}