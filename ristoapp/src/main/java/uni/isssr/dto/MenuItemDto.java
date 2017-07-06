package uni.isssr.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio on 29/06/2017.
 */
public class MenuItemDto {

    private String nomeMenu;
    private String immagineMenu;
    private List<CategoriaDto> categorie;
    private String descrizione;
    private boolean isActive;

    public MenuItemDto(String nomeMenu, String immagineMenu) {
        this.nomeMenu = nomeMenu;
        this.immagineMenu = immagineMenu;
        this.categorie = new ArrayList<>();
    }

    public MenuItemDto() {}

    public String getNomeMenu() {
        return nomeMenu;
    }

    public void setNomeMenu(String nomeMenu) {
        this.nomeMenu = nomeMenu;
    }

    public String getImmagineMenu() {
        return immagineMenu;
    }

    public void setImmagineMenu(String immagineMenu) {
        this.immagineMenu = immagineMenu;
    }

    public List<CategoriaDto> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<CategoriaDto> categorie) {
        this.categorie = categorie;
    }

    public void addCategoria(CategoriaDto categoriaDto) {
        this.categorie.add(categoriaDto);
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}

