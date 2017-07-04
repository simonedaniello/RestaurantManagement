package uni.isssr.dto;

import java.util.List;

public class MenuDto {

    private String nome;

    private List<CategoriaMenuDto> categorie;

    private boolean isActive;

    private String descrizione;

    public MenuDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CategoriaMenuDto> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<CategoriaMenuDto> categorie) {
        this.categorie = categorie;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
