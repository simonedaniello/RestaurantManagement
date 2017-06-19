package uni.isssr.entities;


import javax.persistence.*;

/**
 * Created by francesco on 19/06/17.
 */

@Entity
public class Prodotto {

    @Id
    private Long id;

    private String nome;

    public Prodotto() {
    }

    public Prodotto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
