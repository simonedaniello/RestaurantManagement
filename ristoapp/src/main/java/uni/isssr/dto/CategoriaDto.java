package uni.isssr.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio on 29/06/2017.
 */
public class CategoriaDto {

    private String nomeCategoria;

    private Integer posizione;



    private List<PietanzaMenuDto> pietanze;

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

    public List<PietanzaMenuDto> getPietanze() {
        return pietanze;
    }

    public void setPietanze(List<PietanzaMenuDto> pietanze) {
        this.pietanze = pietanze;
    }

    public void addPietanza(PietanzaMenuDto pietanzaDto) {
        this.pietanze.add(pietanzaDto);
    }
}
