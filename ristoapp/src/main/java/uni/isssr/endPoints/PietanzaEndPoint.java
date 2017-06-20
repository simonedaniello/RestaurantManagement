package uni.isssr.endPoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.PietanzaDto;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.service.PietanzaService;


@RestController
@RequestMapping(path = "/dish")
public class PietanzaEndPoint {

    @Autowired
    private PietanzaRepository pietanzaRepository;

    @Autowired
    private EtichettaRepository etichettaRepository;

    @Autowired
    private PietanzaService pietanzaService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(method = RequestMethod.POST)
    public void addPietanza(@RequestBody PietanzaDto received){
        log.info(received.toString());
        //log.info(received.getNome());
        //log.info(received.getEtichette().get(0).getClassificatore());
        pietanzaRepository.save(pietanzaService.unmarshall(received));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{ID}")
    public void deletePietanza(@PathVariable(value = "ID") Long id){
        pietanzaRepository.delete(id);
    }


   @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Pietanza> getAllPietanze() {

        return pietanzaRepository.findAll();
    }

    /*@RequestMapping(method = RequestMethod.GET)

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
