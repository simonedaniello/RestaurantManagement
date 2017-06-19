package uni.isssr.entities;

import uni.isssr.utilities.IdResoconto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

    public ResocontoPietanza(Long prodottoId, Long preparato) {
        this.id = new IdResoconto(prodottoId);
        this.preparato = preparato;
        this.venduto = new Long(0);
    }

    public ResocontoPietanza(Long prodottoID){
        this.id = new IdResoconto(prodottoID);
        this.preparato = this.venduto = new Long(0);
    }

    public Long getProdottoId() {
        return id.getProdottoId();
    }


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
