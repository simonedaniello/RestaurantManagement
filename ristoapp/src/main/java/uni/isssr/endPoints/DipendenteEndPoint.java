package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.DipendenteDto;
import uni.isssr.entities.Dipendente;
import uni.isssr.service.DipendenteService;

/**
 * Created by Simone on 13/07/2017.
 */


@RestController
@CrossOrigin
@RequestMapping(path = "/dipendenti")
public class DipendenteEndPoint {

    @Autowired
    private DipendenteService dipendenteService;

    @RequestMapping(method = RequestMethod.POST)
    public void addDipendente(@RequestBody DipendenteDto dipendenteDto){
        dipendenteService.save(dipendenteDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{cognome}")
    public void modifyDipendente(@RequestBody DipendenteDto dipendenteDto, @PathVariable(value = "cognome") String oldCognome){
        dipendenteService.updateDipendente(dipendenteDto,oldCognome);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{cognome}")
    public void deletePietanza(@PathVariable(value = "cognome") String cognome){
        dipendenteService.deleteDipendente(cognome);
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Dipendente> getAllTags(){
        return dipendenteService.findAll();
    }
}
