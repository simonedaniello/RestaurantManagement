package uni.isssr.dto;

import java.util.List;

/**
 * Created by francesco on 02/07/17.
 */
public class CategoriaMenuDto {

    private String nomeCategoria;

    private List<PietanzaMenuDto> pietanze;

    public CategoriaMenuDto() {}

    public String getNomeCategoria() {
        return nomeCategoria;
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
}
