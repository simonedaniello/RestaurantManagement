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
}
