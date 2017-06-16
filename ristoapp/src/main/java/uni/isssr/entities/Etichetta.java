package uni.isssr.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
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

    private String classificatore;

    private List<Pietanza> pietanze;

    public Etichetta() {
        this.pietanze = new ArrayList<>();
    }

    public Etichetta(String classificatore) {
        this.classificatore = classificatore;
        this.pietanze = new ArrayList<>();

    }

    @Id
    @Size(max = 32)
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

    @ManyToMany(mappedBy = "etichette")
    @JsonBackReference/* non sarà inviato nel json:necessario per evitare ricorsione e stackoverflow
    //in caso si dovranno usare dei dto */
    public List<Pietanza> getPietanze() {
        return pietanze;
    }

    public void setPietanze(List<Pietanza> pietanze) {
        this.pietanze = pietanze;
    }
}
