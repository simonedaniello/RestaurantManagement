package uni.isssr.entities;

import javax.persistence.*;

@Entity
public class ComandaItem {

    @Id
    @GeneratedValue
    private Long id;

    private String nomePietanza;
    private double prezzoPietanza;
    private int quantita;

    public ComandaItem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePietanza() {
        return nomePietanza;
    }

    public void setNomePietanza(String nomePietanza) {
        this.nomePietanza = nomePietanza;
    }

    public double getPrezzoPietanza() {
        return prezzoPietanza;
    }

    public void setPrezzoPietanza(double prezzoPietanza) {
        this.prezzoPietanza = prezzoPietanza;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
