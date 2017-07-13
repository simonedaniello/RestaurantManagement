package uni.isssr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Simone on 13/07/2017.
 */
@Entity
public class Dipendente {

    @Id
    private String cognome;

    private String nome;
    private String ruolo;

    public Dipendente(){
    }

    public Dipendente(String cognome, String nome, String ruolo){
        this.cognome=cognome;
        this.nome = nome;
        this.ruolo = ruolo;
    }


    public String getCognome() {
        return cognome;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public String getRuolo() {
        return ruolo;
    }
}
