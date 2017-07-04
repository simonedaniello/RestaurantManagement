package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.AnalyticsDto;
import uni.isssr.dto.ResocontoPietanzaDto;
import uni.isssr.entities.ResocontoPietanza;
import uni.isssr.repositories.ResocontoRepository;
import uni.isssr.service.AnalyticService;
import uni.isssr.service.ResocontoService;

import java.util.List;

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

    @Autowired
    private AnalyticService analyticService;

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

    /*@RequestMapping(method = RequestMethod.GET, value = "/{data}")
    public List<String> getResoconti(@PathVariable(value = "data") String data){
        return resocontoRepository.getResocontiByData(data);
    }*/

    @RequestMapping(method = RequestMethod.GET, value = "/{data}")
    public AnalyticsDto getReport(@PathVariable(value = "data") String data){
        return analyticService.getReport(data);
    }
}
