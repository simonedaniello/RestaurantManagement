package uni.isssr.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Categoria {

    public Categoria(String nome) {
        this.nome = nome;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String nome;

    @OneToMany
    private Set<Pietanza> pientanze;
}
