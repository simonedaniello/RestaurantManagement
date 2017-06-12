package uni.isssr.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pietanza {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private double prezzo;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etichetta> etichette;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingrediente> ingredienti;

    public Pietanza() {}

    public Pietanza(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.etichette = new ArrayList<>();
        this.ingredienti = new ArrayList<>();
    }

    public Pietanza(String nome, double prezzo, List<Etichetta> etichette) {
        this.ingredienti = new ArrayList<>();
        this.nome = nome;
        this.etichette = etichette;
        this.prezzo = prezzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public List<Etichetta> getEtichette() {
        return etichette;
    }

    public void setEtichette(List<Etichetta> etichette) {
        this.etichette = etichette;
    }

    public void addIngrediente(Ingrediente ingrediente) {
        this.ingredienti.add(ingrediente);
    }

    public void removeIngrediente(Ingrediente ingrediente) {
        this.ingredienti.remove(ingrediente);
    }

    public void addEtichetta(Etichetta etichetta) {
        this.etichette.add(etichetta);
    }

    public void removeEtichetta(Etichetta etichetta) {
        this.etichette.remove(etichetta);
    }
}
