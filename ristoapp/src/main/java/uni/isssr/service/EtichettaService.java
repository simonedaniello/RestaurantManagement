package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isssr.entities.Etichetta;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.PietanzaRepository;

import java.util.List;

/**
 * Created by francesco on 02/07/17.
 */
@Service
public class EtichettaService {

    @Autowired
    private PietanzaRepository pietanzaRepository;

    @Autowired
    private EtichettaRepository etichettaRepository;

    public void updateEtichetta(String newClassificatore, String oldClassificatore){
        List<Pietanza> pietanze = pietanzaRepository.findToUpdate(oldClassificatore);
        etichettaRepository.saveAndFlush(new Etichetta(newClassificatore));
        for (Pietanza p:pietanze) {
            p.addEtichetta(etichettaRepository.findOne(newClassificatore));
            pietanzaRepository.save(p);
        }
        etichettaRepository.deleteEtichetta(oldClassificatore);
    }
}
