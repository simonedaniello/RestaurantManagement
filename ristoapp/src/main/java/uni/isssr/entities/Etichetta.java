package uni.isssr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    public Etichetta() {
    }

    public Etichetta(String classificatore) {
        this.classificatore = classificatore;

    }

    public String getClassificatore() {
        return classificatore;
    }

    public void setClassificatore(String classificatore) {
        this.classificatore = classificatore;
    }

}
