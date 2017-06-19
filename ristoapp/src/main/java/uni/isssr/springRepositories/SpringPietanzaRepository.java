package uni.isssr.springRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uni.isssr.dto.IngredienteDto;
import uni.isssr.dto.PietanzaDto;
import uni.isssr.entities.Etichetta;
import uni.isssr.entities.Ingrediente;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.EtichettaRepository;
import uni.isssr.repositories.PietanzaRepository;

import java.util.ArrayList;

/**
 * Created by francesco on 19/06/17.
 */

@Repository
public class SpringPietanzaRepository {

    @Autowired
    private PietanzaRepository pietanzaRepository;

    @Autowired
    private EtichettaRepository etichettaRepository;

    public void salva(PietanzaDto pietanzaDto){
        Pietanza pietanza = new Pietanza(pietanzaDto.getNome(), pietanzaDto.getPrezzo());
        for ( String etichettaId: pietanzaDto.getEtichette()) {
            Etichetta etichetta = etichettaRepository.findOne(etichettaId);
            pietanza.addEtichetta(etichetta);
        }
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        for ( IngredienteDto ingredienteDto: pietanzaDto.getIngredienti()) {
            Ingrediente ingrediente = new Ingrediente(ingredienteDto.getNomeProdotto(), ingredienteDto.getQuantita());
            ingrediente.setIdProdotto(new Long(5));
            ingredientes.add(ingrediente);
        }
        pietanza.setIngredienti(ingredientes);
        pietanzaRepository.save(pietanza);
    }
}
