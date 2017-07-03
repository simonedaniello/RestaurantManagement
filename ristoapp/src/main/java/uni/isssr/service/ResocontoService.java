package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isssr.dto.ResocontoPietanzaDto;
import uni.isssr.entities.ResocontoPietanza;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.repositories.ResocontoRepository;
import uni.isssr.utilities.IdResoconto;

import java.util.Date;

/**
 * Created by francesco on 19/06/17.
 */

@Service
public class ResocontoService {

    @Autowired
    private ResocontoRepository resocontoRepository;

    @Autowired
    private PietanzaRepository pietanzaRepository;

    public ResocontoPietanza unmarshall(ResocontoPietanzaDto resocontoPietanzaDto){
        ResocontoPietanza resocontoPietanza = resocontoRepository.findOne(
                new IdResoconto(pietanzaRepository.findOne(resocontoPietanzaDto.getProdottoId())));
        if(resocontoPietanza == null){
            resocontoPietanza = new ResocontoPietanza(pietanzaRepository.findOne(
                    resocontoPietanzaDto.getProdottoId()));
        }
        return resocontoPietanza;
    }

    public ResocontoPietanza updateVenduto(ResocontoPietanzaDto resocontoPietanzaDto){
        ResocontoPietanza resocontoPietanza = this.unmarshall(resocontoPietanzaDto);
        resocontoPietanza.addVenduto(resocontoPietanzaDto.getValue());
        return resocontoPietanza;
    }

    public ResocontoPietanza updatePreparato(ResocontoPietanzaDto resocontoPietanzaDto){
        ResocontoPietanza resocontoPietanza = this.unmarshall(resocontoPietanzaDto);
        resocontoPietanza.addPreparato(resocontoPietanzaDto.getValue());
        return resocontoPietanza;
    }
}
