package uni.isssr.endPoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.Etichetta;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.PietanzaRepository;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/dish")
public class PietanzaEndPoint {

    @Autowired
    private PietanzaRepository pietanzaRepository;

    @Autowired EtichettaRepository etichettaRepository;

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
        Etichetta e = new Etichetta("mhgjhghj tPiccante");
        Etichetta e1 = new Etichetta("Piccantissimo");
        //etichettaRepository.save(e);
        //etichettaRepository.save(e1); // ci apettimao che etichette siano già salvate
        Pietanza p = new Pietanza("Pasta al sugo",15.0, new ArrayList<Etichetta>() {
        });
        Pietanza p1 = new Pietanza("Pasta al pesto", 15.0, new ArrayList<Etichetta>() {
        });
        p.addEtichetta(e);
        p1.addEtichetta(e);
        pietanzaRepository.save(p);
        pietanzaRepository.save(p1);
        p1.addEtichetta(e1);
        p.addEtichetta(e1);
        //e.addPietanza(p);
        //e.addPietanza(p1);  //---- non bisogna farlo -> bisogna aggiungere solo alle pietanze ----
        p.removeEtichetta(e);
        return pietanzaRepository.findAll();
    } /*

    @RequestMapping(method = RequestMethod.GET)

    public @ResponseBody
    List<Pietanza> getAllPietanze() {
        Etichetta e = new Etichetta("mhgjhghj tPiccante");
        etichettaRepository.save(e);
        Pietanza p = new Pietanza("Pasta al sugo",15.0, new ArrayList<Etichetta>() {
        });
        Pietanza p1 = new Pietanza("Pasta al pesto", 15.0, new ArrayList<Etichetta>() {
        });
        p.addEtichetta(e);
        p1.addEtichetta(e);
        pietanzaRepository.save(p);
        pietanzaRepository.save(p1);
        e.addPietanza(p);
        e.addPietanza(p1);
        return etichettaRepository.findAll().get(0).getPietanze();
    } */

}
