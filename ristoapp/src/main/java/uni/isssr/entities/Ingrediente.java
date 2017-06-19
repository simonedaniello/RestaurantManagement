package uni.isssr.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Ingrediente {


    @Id
    @GeneratedValue
    private Long id;

    /*
        Attributi seguenti sono presi dal Json
     */
    @OneToOne
    private Prodotto prodotto;

    // quantità è in grammi
    private double quantita;

    public Ingrediente() {
    }

    public Ingrediente(Prodotto prodotto, double quantita) {
        this.prodotto = prodotto;

        this.quantita = quantita;
    }

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
}
