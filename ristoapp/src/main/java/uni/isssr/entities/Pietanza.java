package uni.isssr.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Pietanza {

    private Long id;

    private String nome;

    private Double prezzo;

    private List<Etichetta> etichette;

    private List<Ingrediente> ingredienti;

    public Pietanza() {}

    public Pietanza(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = new Double(prezzo);
        this.etichette = new ArrayList<>();
        this.ingredienti = new ArrayList<>();
    }

    public Pietanza(String nome, double prezzo, ArrayList<Etichetta> etichette) {
        this.ingredienti = new ArrayList<>();
        this.nome = nome;
        this.etichette = etichette;
        this.prezzo = prezzo;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL)
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
        return prezzo.doubleValue();
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pietanza_etichette", joinColumns = {
            @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "classificatore", referencedColumnName = "classificatore",
                    nullable = false, updatable = false) })

    @JsonManagedReference // serve per mostrare le etichette tramite json senza che si vada in ricorsione
    public List<Etichetta> getEtichette() {
        return etichette;
    }

    public void setEtichette(List<Etichetta> etichette) {
        this.etichette = etichette;
    }

    public void addEtichetta(Etichetta etichetta) {
        this.etichette.add(etichetta);
    }


}
