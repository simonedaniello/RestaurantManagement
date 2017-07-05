package uni.isssr.dto;

import java.util.List;

public class PietanzaMenuDto {

    private Long id;

    private PietanzaDto pietanzaDto;

    /*private String nome;

    private Double prezzo;

    private List<String> etichette;

    private List<IngredienteDto> ingredienti;


    public List<IngredienteDto> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<IngredienteDto> ingredienti) {
        this.ingredienti = ingredienti;
    }

    */
    public PietanzaMenuDto(){
    }

    public PietanzaMenuDto(Long id, String nome, double prezzo, List<String> etichette, List<IngredienteDto> ingredienti){
        this.id = id;
        this.pietanzaDto = new PietanzaDto(nome, prezzo, etichette, ingredienti);
    }

    public PietanzaMenuDto(String nome, Long id){
        this.id = id;
        this.pietanzaDto = new PietanzaDto(nome);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public String getNome() {
        return this.pietanzaDto.getNome();
    }

    public void setNome(String nome) {
        this.pietanzaDto.setNome(nome);
    }

    public Double getPrezzo() {
        return this.pietanzaDto.getPrezzo();
    }

    public void setPrezzo(Double prezzo) {this.pietanzaDto.setPrezzo(prezzo);}

    public List<String> getEtichette() {return this.pietanzaDto.getEtichette();}

    public void setEtichette(List<String> etichette) {this.pietanzaDto.setEtichette(etichette);}

    public List<IngredienteDto> getIngredienti() {
        return pietanzaDto.getIngredienti();
    }

    public void setIngredienti(List<IngredienteDto> ingredienti) {
        this.pietanzaDto.setIngredienti(ingredienti);
    }*/

    public PietanzaDto getPietanzaDto() {
        return pietanzaDto;
    }

    public void setPietanzaDto(PietanzaDto pietanzaDto) {
        this.pietanzaDto = pietanzaDto;
    }
}


