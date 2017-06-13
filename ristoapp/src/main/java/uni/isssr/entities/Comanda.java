package uni.isssr.entities;

import javax.persistence.*;

@Entity
public class Comanda {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private int quantita;

    public Comanda() {}

    public Comanda(String nome) {
        this.nome = nome;
    }

    public Comanda(String nome, int quantita) {
        this.nome = nome;
        this.quantita = quantita;
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

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
