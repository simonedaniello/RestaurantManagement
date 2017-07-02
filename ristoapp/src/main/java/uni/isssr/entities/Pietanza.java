package uni.isssr.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Pietanza {

    @Id
    @GeneratedValue
    private Long id;

    /*
        Usato per la ricerca di una Pietanza per nome associata ad una ComandaItem
     */
    @Column(unique = true)
    private String nome;

    private Double prezzo;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "pietanza_etichette", joinColumns = {
            @JoinColumn(name = "id", updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "classificatore",
                    updatable = false)
            })

    @JsonManagedReference (value = "Etichetta")// serve per mostrare le etichette tramite json senza che
    private List<Etichetta> etichette;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingrediente> ingredienti;

    public Pietanza() {}

    public Pietanza(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = new Double(prezzo);
        this.etichette = new ArrayList<>();
        this.ingredienti = new ArrayList<>();
    }

    public Pietanza(Long id, String nome, double prezzo) {
        this.id = id;
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
        return prezzo.doubleValue();
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public List<Etichetta> getEtichette() {
        return etichette;
    }

    public void setEtichette(List<Etichetta> etichette) {
        this.etichette = etichette;
    }

    public void addEtichetta(Etichetta etichetta) {
        this.etichette.add(etichetta);
    }

    public void removeEtichetta(Etichetta etichetta){this.etichette.remove(etichetta);}


}
