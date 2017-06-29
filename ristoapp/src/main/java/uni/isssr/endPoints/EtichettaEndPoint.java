package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.EtichettaDto;
import uni.isssr.entities.Etichetta;
import uni.isssr.repositories.EtichettaRepository;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/tags")
public class EtichettaEndPoint {

    @Autowired
    private EtichettaRepository etichettaRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void addTag(@RequestBody EtichettaDto etichettaDto){
        Etichetta etichetta = new Etichetta(etichettaDto.getClassificatore());
        etichettaRepository.save(etichetta);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{ID}")
    public void deletePietanza(@PathVariable(value = "ID") String id){
        etichettaRepository.deleteEtichetta(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Etichetta> getAllTags(){
        List<Etichetta> lista = etichettaRepository.findAll();
        System.out.println();
        System.out.println();
        System.out.println();
        for (Etichetta e : lista) {
            System.out.println(e.getClassificatore());
        }
        System.out.println();
        System.out.println();
        System.out.println();
        return lista;
    }
}
