package uni.isssr.endPoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.PietanzaDto;
import uni.isssr.entities.Etichetta;
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
        pietanzaRepository.save(pietanzaService.unmarshall(received));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public void updatePietanza(@RequestBody PietanzaDto received, @PathVariable(value = "ID") Long id){
        pietanzaRepository.save(pietanzaService.unmarshall(id, received));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{ID}")
    public void deletePietanza(@PathVariable(value = "ID") Long id){
        pietanzaRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public @ResponseBody Pietanza getPietanzaById(@RequestParam(value = "id") Long id){
        return pietanzaRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Page<Pietanza> searchPietanza(@RequestParam(value = "nome", defaultValue = "") String nome, @RequestParam(value = "tags", defaultValue = "") String[] tags, Pageable pageable){
        if (tags.length == 0) return pietanzaRepository.findAllByNomeContainingOrderByNome(nome, pageable);
        Etichetta[] etichette = pietanzaService.convertToEtichette(tags);
        return pietanzaRepository.findDistinctByNomeContainingAndEtichetteIn(nome, etichette,pageable);
    }

}
