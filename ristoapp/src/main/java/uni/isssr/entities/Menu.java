package uni.isssr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Menu {

    @Id
    private String nome;

    private String descrizione;

    private String imageString;

    /* Lo sviluppatore deve assicurarsi che ci sia un solo menù attivo per volta */
    private boolean isActive;

    private Date dataCreazione;

    @OneToMany
    private List<Categoria> categorie;

    public Menu() {

    }

    public Menu(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.categorie = new ArrayList<>();
        this.dataCreazione = new Date();
    }

    public Menu(String nome, String descrizione, List<Categoria> categorie) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.categorie = categorie;
        this.dataCreazione = new Date();
    }


    public Menu(String nome, String descrizione, String imageString) {
        this(nome, descrizione);
        this.imageString = imageString;
    }


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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }
}
