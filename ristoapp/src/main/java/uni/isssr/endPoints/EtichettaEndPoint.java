package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.EtichettaDto;
import uni.isssr.entities.Etichetta;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.service.EtichettaService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/tags")
public class EtichettaEndPoint {

    @Autowired
    private EtichettaRepository etichettaRepository;

    @Autowired
    private EtichettaService etichettaService;

    @RequestMapping(method = RequestMethod.POST)
    public void addTag(@RequestBody EtichettaDto etichettaDto){
        Etichetta etichetta = new Etichetta(etichettaDto.getClassificatore());
        etichettaRepository.save(etichetta);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public void modifyTag(@RequestBody EtichettaDto etichettaDto, @PathVariable(value = "ID") String oldClassificatore){
        etichettaService.updateEtichetta(etichettaDto.getClassificatore(),oldClassificatore);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ID}")
    public void deletePietanza(@PathVariable(value = "ID") String id){
        etichettaRepository.deleteEtichetta(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Etichetta> getAllTags(){
        return etichettaRepository.findAll();
    }
}
