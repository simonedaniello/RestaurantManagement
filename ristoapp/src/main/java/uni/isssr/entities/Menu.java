package uni.isssr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    public Menu(String nome, String descrizione, String chef, String imageString) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.chef = chef;
        this.imageString = imageString;
        this.categorie = new ArrayList<>();
    }

    public Menu() {
    }

    @Id
    private String nome;

    private String descrizione;

    private String chef;

    private String imageString;

    /*da ricontrollare in quanto ci sono dubbi sul mappaggio sul db*/
    @OneToMany
    private List<Categoria> categorie;

    public void addCategoria(Categoria c){
        categorie.add(c);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }
}
