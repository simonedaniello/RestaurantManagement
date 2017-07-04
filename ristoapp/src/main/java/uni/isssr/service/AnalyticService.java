package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isssr.dto.AnalyticsDto;
import uni.isssr.dto.PietanzaAnalyticsDto;
import uni.isssr.entities.ResocontoPietanza;
import uni.isssr.repositories.CategoriaRepository;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.repositories.ResocontoRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by francesco on 03/07/17.
 */

@Service
public class AnalyticService {

    @Autowired
    private ResocontoRepository resocontoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PietanzaService pietanzaService;

    public AnalyticsDto getReport(String data){

        AnalyticsDto analyticsDto = new AnalyticsDto(data);
        List<ResocontoPietanza> resocontoPietanze = resocontoRepository.findAllByData(data);
        List<PietanzaAnalyticsDto> pietanzaAnalyticsDtos = new ArrayList<>();
        for (ResocontoPietanza rp:resocontoPietanze) {
               pietanzaAnalyticsDtos.add(new PietanzaAnalyticsDto(
                       pietanzaService.marshall(rp.getPietanza()), rp.getVenduto(), rp.getPreparato()));

        }
        analyticsDto.setPietanzaAnalyticsDtos(pietanzaAnalyticsDtos);
        return analyticsDto;
    }
}
