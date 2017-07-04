package uni.isssr.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio on 29/06/2017.
 */
public class CategoriaDto {

    private String nomeCategoria;

    private Integer posizione;



    private List<PietanzaDto> pietanze;

    public CategoriaDto() {}

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Integer getPosizione() {
        return posizione;
    }

    public void setPosizione(Integer posizione) {
        this.posizione = posizione;
    }
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public List<PietanzaDto> getPietanze() {
        return pietanze;
    }

    public void setPietanze(List<PietanzaDto> pietanze) {
        this.pietanze = pietanze;
    }

    public void addPietanza(PietanzaDto pietanzaDto) {
        this.pietanze.add(pietanzaDto);
    }
}
