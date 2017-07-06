package uni.isssr.dto;

import java.util.List;

public class PietanzaMenuDto {

    private Long id;

    private PietanzaDto pietanzaDto;

    public PietanzaMenuDto(){
    }

    public PietanzaMenuDto(Long id, String nome, double prezzo, List<String> etichette, List<IngredienteDto> ingredienti){
        this.id = id;
        this.pietanzaDto = new PietanzaDto(nome, prezzo, etichette, ingredienti);
    }

    public PietanzaMenuDto(Long id, String nome, double prezzo, List<IngredienteDto> ingredienti){
        this.id = id;
        this.pietanzaDto = new PietanzaDto(nome, prezzo, ingredienti);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PietanzaDto getPietanzaDto() {
        return pietanzaDto;
    }

    public void setPietanzaDto(PietanzaDto pietanzaDto) {
        this.pietanzaDto = pietanzaDto;
    }
}


