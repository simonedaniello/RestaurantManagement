package uni.isssr.endPoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.PietanzaDto;
import uni.isssr.dto.PietanzaMenuDto;
import uni.isssr.entities.Etichetta;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.service.PietanzaService;

import java.util.List;


@RestController
@RequestMapping(path = "/dish")
public class PietanzaEndPoint {

    @Autowired
    private PietanzaService pietanzaService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    //Salvataggio di una nuova Pietanza: avviene prima una trasformazione da PietanzaDto a Pietanza
    @RequestMapping(method = RequestMethod.POST)
    public void addPietanza(@RequestBody PietanzaDto received){
        pietanzaService.save(received);
    }

    //Aggiornamento di una pietanza
    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public void updatePietanza(@RequestBody PietanzaDto received, @PathVariable(value = "ID") Long id){
        pietanzaService.update(id, received);
    }

    //eliminazione di una pietanza
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{ID}")
    public void deletePietanza(@PathVariable(value = "ID") Long id){
        pietanzaService.deletePietanza(id);
    }

    //Retrieve di una pietanza tramite il suo ID
    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public @ResponseBody Pietanza getPietanzaById(@RequestParam(value = "id") Long id){
        return pietanzaService.select(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public @ResponseBody List<PietanzaMenuDto> getAllPietanze(){
        return pietanzaService.findAll();
    }

    //Search delle pietanze
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Page<Pietanza> searchPietanza(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                       @RequestParam(value = "tags", defaultValue = "") String[] tags, Pageable pageable){
        return pietanzaService.search(nome, tags, pageable);
    }

}
