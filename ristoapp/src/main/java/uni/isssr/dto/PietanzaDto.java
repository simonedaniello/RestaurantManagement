package uni.isssr.dto;

import uni.isssr.entities.Ingrediente;

import java.util.List;

/**
 * Created by francesco on 19/06/17.
 */

public class PietanzaDto {

    private String nome;

    private Double prezzo;

    private List<String> etichette;

    private List<IngredienteDto> ingredienti;


    public List<IngredienteDto> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<IngredienteDto> ingredienti) {
        this.ingredienti = ingredienti;
    }


    public PietanzaDto(){
    }

    public PietanzaDto(String nome, double prezzo, List<String> etichette, List<IngredienteDto> ingredienti){
        this.nome = nome;
        this.prezzo = new Double(prezzo);
        this.etichette = etichette;
        this.ingredienti = ingredienti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public List<String> getEtichette() {
        return etichette;
    }

    public void setEtichette(List<String> etichette) {
        this.etichette = etichette;
    }
}
