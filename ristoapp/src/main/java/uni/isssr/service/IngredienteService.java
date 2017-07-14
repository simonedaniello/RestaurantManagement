package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isssr.dto.IngredienteDto;
import uni.isssr.entities.Ingrediente;
import uni.isssr.entities.Prodotto;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.repositories.ProdottoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by balmung on 05/07/17.
 */
@Service
public class IngredienteService {

    @Autowired
    private ProdottoRepository prodottoRepository;


    //trasforma gli ingredientiDto di una pietanza in Ingredienti
    public List<Ingrediente> marshal(List<IngredienteDto> ingredientiDto){

        List<Ingrediente> ingredienti = new ArrayList<>();
        for ( IngredienteDto ingredienteDto: ingredientiDto) {
            Prodotto prodotto = prodottoRepository.findOne(ingredienteDto.getProdottoId());
            if(prodotto == null){
                //l'ingrediente non esiste nel database e viene quindi aggiunto
                prodotto = new Prodotto(ingredienteDto.getProdottoId(), ingredienteDto.getNome());
                prodottoRepository.save(prodotto);
            }

            Ingrediente ingrediente = new Ingrediente(prodotto, ingredienteDto.getQuantita());
            ingredienti.add(ingrediente);
        }

        return ingredienti;
    }
}
