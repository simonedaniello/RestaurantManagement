package uni.isssr.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Pietanza {

    public Pietanza() {
    }

    public Pietanza(List<Ingrediente> ingredienti, String nome, Set<Etichetta> etichette) {
        this.ingredienti = ingredienti;
        this.nome = nome;
        this.etichette = etichette;
    }

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @OneToMany
    @JsonProperty
    private List<Ingrediente> ingredienti;

    @JsonProperty
    private String nome;

    @ManyToMany
    @JsonProperty
    private Set<Etichetta> etichette;

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

    public Set<Etichetta> getEtichette() {
        return etichette;
    }

    public void setEtichette(Set<Etichetta> etichette) {
        this.etichette = etichette;
    }
}
