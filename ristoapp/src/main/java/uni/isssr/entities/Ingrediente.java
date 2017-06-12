package uni.isssr.entities;


import javax.persistence.*;

@Entity
public class Ingrediente {


    @Id
    @GeneratedValue
    private Long id;

    /*
        Attributi seguenti sono presi dal Json
     */
    private Long idProdotto;
    private String nomeProdotto;

    // quantità è in grammi
    private double quantita;

    public Ingrediente() {
    }

    public Ingrediente(Long idProdotto, String nomeProdotto, double quantita) {
        this.idProdotto = idProdotto;
        this.nomeProdotto = nomeProdotto;
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

    public Long getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Long idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }
}
