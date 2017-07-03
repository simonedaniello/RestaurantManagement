package uni.isssr.entities;

import uni.isssr.utilities.IdResoconto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by francesco on 19/06/17.
 */

@Entity
public class ResocontoPietanza {

    @EmbeddedId
    private IdResoconto id;

    private Long venduto;

    private Long preparato;

    public ResocontoPietanza() {
    }

    public ResocontoPietanza(Pietanza pietanza, Long preparato) {
        this.id = new IdResoconto(pietanza);
        this.preparato = preparato;
        this.venduto = new Long(0);
    }

    public ResocontoPietanza(Pietanza pietanza){
        this.id = new IdResoconto(pietanza);
        this.preparato = this.venduto = new Long(0);
    }

    public Pietanza getPietanza() {
        return id.getPietanza();
    }

    public Long getPietanzaId(){return this.id.getPietanza().getId();}


    public String getData() {
        return id.getData();
    }

    public Long getVenduto() {
        return venduto;
    }

    public void addVenduto(Long venduto) {
        this.venduto += venduto;
    }

    public Long getPreparato() {
        return preparato;
    }

    public void addPreparato(Long preparato) {
        this.preparato += preparato;
    }
}
