package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isssr.dto.IngredienteDto;
import uni.isssr.dto.PietanzaDto;
import uni.isssr.entities.Etichetta;
import uni.isssr.entities.Ingrediente;
import uni.isssr.entities.Pietanza;
import uni.isssr.entities.Prodotto;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.ProdottoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francesco on 19/06/17.
 */

@Service
public class PietanzaService {

    @Autowired
    private EtichettaRepository etichettaRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;


    public Pietanza unmarshall(PietanzaDto pietanzaDto){
        Pietanza pietanza = new Pietanza(pietanzaDto.getNome(), pietanzaDto.getPrezzo());
        for ( String etichettaId: pietanzaDto.getEtichette()) {
            Etichetta etichetta = etichettaRepository.findOne(etichettaId);
            pietanza.addEtichetta(etichetta);
        }
        List<Ingrediente> ingredienti = new ArrayList<>();
        for ( IngredienteDto ingredienteDto: pietanzaDto.getIngredienti()) {

            Prodotto prodotto = prodottoRepository.findOne(ingredienteDto.getProdottoId());
            if(prodotto == null){
                prodotto = new Prodotto(ingredienteDto.getProdottoId(), ingredienteDto.getNomeProdotto());
                prodottoRepository.save(prodotto);
            }

            Ingrediente ingrediente = new Ingrediente(prodotto, ingredienteDto.getQuantita());
            ingredienti.add(ingrediente);
        }
        pietanza.setIngredienti(ingredienti);
        return pietanza;
    }
}
