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

    public MenuItemDto(String nomeMenu, String immagineMenu) {
        this.nomeMenu = nomeMenu;
        this.immagineMenu = immagineMenu;
        this.categorie = new ArrayList<>();
    }

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
}

