package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uni.isssr.dto.ResocontoPietanzaDto;
import uni.isssr.entities.ResocontoPietanza;
import uni.isssr.repositories.ResocontoRepository;
import uni.isssr.service.ResocontoService;

/**
 * Created by francesco on 19/06/17.
 */
@RestController
@RequestMapping(path = "/resoconto")
public class ResocontoEndPoint {

    @Autowired
    private ResocontoService resocontoService;

    @Autowired
    private ResocontoRepository resocontoRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/venduto")
    public void aggiornaResocontoVenduto(@RequestBody ResocontoPietanzaDto resocontoPietanzaDto){
        ResocontoPietanza resocontoPietanza = resocontoService.updateVenduto(resocontoPietanzaDto);
        resocontoRepository.save(resocontoPietanza);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/preparato")
    public void aggiornaResocontoPreparato(@RequestBody ResocontoPietanzaDto resocontoPietanzaDto){
        ResocontoPietanza resocontoPietanza = resocontoService.updatePreparato(resocontoPietanzaDto);
        resocontoRepository.save(resocontoPietanza);

    }
}
