package uni.isssr.dto;

import java.util.List;

/**
 * Created by francesco on 02/07/17.
 */
public class CategoriaMenuDto {

    private String nomeCategoria;

    private List<PietanzaMenuDto> pietanze;

    private  Integer posizione;

    private Long id;

    public Integer getPosizione() {
        return posizione;
    }

    public void setPosizione(Integer posizione) {
        this.posizione = posizione;
    }

    public CategoriaMenuDto() {}

    public CategoriaMenuDto(String nomeCategoria, List<PietanzaMenuDto> pietanze, Integer posizione) {
        this.nomeCategoria = nomeCategoria;
        this.pietanze = pietanze;
        this.posizione = posizione;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
