package uni.isssr.entities;


import javax.persistence.*;

@Entity
public class Ingrediente {

    public Ingrediente() {
    }

    public Ingrediente(double quantita, Prodotto prodotto) {
        this.quantita = quantita;
        this.prodotto = prodotto;
    }

    @Id
    @GeneratedValue
    private Long id;

    // quantità è in grammi
    private double quantita;

    @ManyToOne
    private Prodotto prodotto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
}
