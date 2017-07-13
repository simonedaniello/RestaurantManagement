package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isssr.dto.DipendenteDto;
import uni.isssr.entities.Dipendente;

import uni.isssr.repositories.DipendenteRepository;

/**
 Author : Simone D'Aniello
 Date :  13/07/2017.
 */
@Service
public class DipendenteService {


    @Autowired
    private DipendenteRepository dipendenteRepository;


    public void updateDipendente(DipendenteDto newDipendente, String oldCognome){
        dipendenteRepository.deleteDipendente(oldCognome);
        save(newDipendente);
    }

    public void save(DipendenteDto dipendenteDto) {
        Dipendente dipendente= new Dipendente(dipendenteDto.getCognome(), dipendenteDto.getNome(), dipendenteDto.getRuolo());
        dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente(String cognome) {
        dipendenteRepository.deleteDipendente(cognome);
    }

    public Iterable<Dipendente> findAll() {
        return dipendenteRepository.findAll();
    }


}
