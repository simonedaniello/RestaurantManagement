package uni.isssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uni.isssr.dto.IngredienteDto;
import uni.isssr.dto.PietanzaDto;
import uni.isssr.dto.PietanzaMenuDto;
import uni.isssr.entities.*;
import uni.isssr.repositories.CategoriaRepository;
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
    private PietanzaRepository pietanzaRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private IngredienteService ingredienteService;

    public void save(PietanzaDto pietanzaDto) {
        pietanzaRepository.save(this.unmarshall(pietanzaDto));
    }

    public void update(Long id, PietanzaDto pietanzaDto) {
        pietanzaRepository.save(this.unmarshall(id, pietanzaDto));
    }

    public Pietanza select(Long id) {
        return pietanzaRepository.findOne(id);
    }

    public Page<Pietanza> search(String nome, String[] tags, Pageable pageable) {
        if (tags.length == 0) return pietanzaRepository.findAllByNomeContainingOrderByNome(nome, pageable);
        Etichetta[] etichette = this.convertToEtichette(tags);
        return pietanzaRepository.findDistinctByNomeContainingAndEtichetteIn(nome, etichette,pageable);
    }


    //trasformazione di un pietanzaDto in Pietanza
    public Pietanza unmarshall(PietanzaDto pietanzaDto){
        Pietanza pietanza = new Pietanza(pietanzaDto.getNome(), pietanzaDto.getPrezzo());
        for ( String etichettaId: pietanzaDto.getEtichette()) {
            Etichetta etichetta = etichettaRepository.findOne(etichettaId);
            pietanza.addEtichetta(etichetta);
        }
        /*List<Ingrediente> ingredienti = new ArrayList<>();
        for ( IngredienteDto ingredienteDto: pietanzaDto.getIngredienti()) {
            Prodotto prodotto = prodottoRepository.findOne(ingredienteDto.getProdottoId());
            if(prodotto == null){
                prodotto = new Prodotto(ingredienteDto.getProdottoId(), ingredienteDto.getNome());
                prodottoRepository.save(prodotto);
            }

            Ingrediente ingrediente = new Ingrediente(prodotto, ingredienteDto.getQuantita());
            ingredienti.add(ingrediente);
        }*/
        pietanza.setIngredienti(ingredienteService.marshal(pietanzaDto.getIngredienti()));
        return pietanza;
    }

    public Pietanza unmarshall(Long id, PietanzaDto pietanzaDto){
        Pietanza pietanza = this.unmarshall(pietanzaDto);       /*new Pietanza(id, pietanzaDto.getNome(), pietanzaDto.getPrezzo());
        for ( String etichettaId: pietanzaDto.getEtichette()) {
            Etichetta etichetta = etichettaRepository.findOne(etichettaId);
            pietanza.addEtichetta(etichetta);
        }
        List<Ingrediente> ingredienti = new ArrayList<>();
        for ( IngredienteDto ingredienteDto: pietanzaDto.getIngredienti()) {

            Prodotto prodotto = prodottoRepository.findOne(ingredienteDto.getProdottoId());
            if(prodotto == null){
                prodotto = new Prodotto(ingredienteDto.getProdottoId(), ingredienteDto.getNome());
                prodottoRepository.save(prodotto);
            }

            Ingrediente ingrediente = new Ingrediente(prodotto, ingredienteDto.getQuantita());
            ingredienti.add(ingrediente);
        }
        pietanza.setIngredienti(ingredienteService.marshal(pietanzaDto.getIngredienti()));*/
        pietanza.setId(id);
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

    public PietanzaMenuDto marshall(Pietanza pietanza){
        List<IngredienteDto> ingredienti = new ArrayList<>();
        for (Ingrediente ingrediente:pietanza.getIngredienti()){
            IngredienteDto ingredienteDto = menuService.ingredienteToIngredienteDto(ingrediente);
            ingredienti.add(ingredienteDto);
            System.out.println(ingredienteDto.getNome());
        }
        List<String> etichette = new ArrayList<>();
        for (Etichetta etichetta:pietanza.getEtichette()){
            etichette.add(etichetta.getClassificatore());
        }
        return new PietanzaMenuDto(pietanza.getId(), pietanza.getNome(), pietanza.getPrezzo(),
                etichette, ingredienti);
    }

    public List<PietanzaMenuDto> findAll(){
        List<Pietanza> pietanze = pietanzaRepository.findAll();
        List<PietanzaMenuDto> pietanzaMenuDtos = new ArrayList<>();
        for(Pietanza pietanza: pietanze){
            pietanzaMenuDtos.add(this.marshall(pietanza));
        }
        return pietanzaMenuDtos;
    }

    public void deletePietanza(Long id){
        // prendo le categorie da aggiornare
        List<Categoria> categorie = categoriaRepository.selectToDeletePietanza(id);
        // prendo la pietanza da eliminare
        Pietanza pietanza = pietanzaRepository.findOne(id);
        for(Categoria categoria : categorie){
            categoria.getPietanze().remove(pietanza);
            categoriaRepository.save(categoria);
        }
        pietanzaRepository.delete(pietanza);
    }
}
