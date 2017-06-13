package uni.isssr.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Etichetta {

    /* Valori tipici sono:
        carne, pesce, piccante, vegetariano
         */
    @Id
    @Size(max = 32)
    private String classificatore;

    @ManyToMany(mappedBy = "etichette", fetch = FetchType.EAGER)
    private List<Pietanza> pietanze;

    public Etichetta() {
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
