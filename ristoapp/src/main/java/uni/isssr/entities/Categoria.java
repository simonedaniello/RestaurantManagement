package uni.isssr.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Categoria {
    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(Set<Pietanza> pietanze, String nome) {
        this.nome = nome;
        this.pientanze = pietanze;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @JsonProperty
    private String nome;

    @OneToMany
    @JsonProperty
    private Set<Pietanza> pientanze;
}
