package uni.isssr.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Antonio on 30/06/2017.
 */
@Entity
public class ComandaOrder {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ComandaItem> comandaItems;

    private int tavolo;
    private boolean active;

    public ComandaOrder() {}

    public List<ComandaItem> getComandaItems() {
        return comandaItems;
    }

    public void setComandaItems(List<ComandaItem> comandaItems) {
        this.comandaItems = comandaItems;
    }

    public int getTavolo() {
        return tavolo;
    }

    public void setTavolo(int tavolo) {
        this.tavolo = tavolo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
