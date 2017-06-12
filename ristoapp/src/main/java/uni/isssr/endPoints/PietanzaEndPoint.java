package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.PietanzaRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/dish")
public class PietanzaEndPoint {

    @Autowired
    private PietanzaRepository pietanzaRepository;


    @RequestMapping(method = RequestMethod.POST)
    public void addPietanza(@RequestBody Pietanza pietanza){
        pietanzaRepository.save(pietanza);
    }


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Pietanza> getAllPietanze() {
        return pietanzaRepository.findAll();
    }

}