package uni.isssr.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Etichetta {

    /* Valori tipici sono:
        carne, pesce, piccante, vegetariano
         */
    @Id
    @Size(max = 32)
    private String classificatore;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "etichette")
    @JsonBackReference (value = "Pietanza")
    private List<Pietanza> pietanze;

    public Etichetta() {
        this.pietanze = new ArrayList<>();
    }

    public Etichetta(String classificatore) {
        this.classificatore = classificatore;
        this.pietanze = new ArrayList<>();

    }


    public String getClassificatore() {
        return classificatore;
    }

    public void setClassificatore(String classificatore) {
        this.classificatore = classificatore;
    }

    public void addPietanza(Pietanza pietanza) {
        this.pietanze.add(pietanza);
        pietanza.getEtichette().add(this);
    }

    public void removePietanza(Pietanza pietanza) {
        this.pietanze.remove(pietanza);
        pietanza.getEtichette().remove(this);
    }

    public List<Pietanza> getPietanze() {
        return pietanze;
    }

    public void setPietanze(List<Pietanza> pietanze) {
        this.pietanze = pietanze;
    }
}
