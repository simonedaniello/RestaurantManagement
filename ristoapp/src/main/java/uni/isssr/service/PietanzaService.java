package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uni.isssr.dto.IngredienteDto;
import uni.isssr.dto.PietanzaDto;
import uni.isssr.entities.Etichetta;
import uni.isssr.entities.Ingrediente;
import uni.isssr.entities.Pietanza;
import uni.isssr.entities.Prodotto;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.PietanzaRepository;
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

    @Autowired
    private PietanzaRepository pietanzaRepository;


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

    public Pietanza unmarshall(Long id, PietanzaDto pietanzaDto){
        Pietanza pietanza = new Pietanza(id, pietanzaDto.getNome(), pietanzaDto.getPrezzo());
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

    public Page<Pietanza> listAllByPage(Pageable pageable){
        return pietanzaRepository.findAll(pageable);
    }

    public Etichetta[] convertToEtichette(String[] tags){
        ArrayList<Etichetta> list = new ArrayList<>();
        for (int i = 0; i < tags.length; i++){
            Etichetta et = etichettaRepository.findOne(tags[i]);
            list.add(et);
        }
        Etichetta[] array = new Etichetta[list.size()];
        return list.toArray(array);
    }
}
