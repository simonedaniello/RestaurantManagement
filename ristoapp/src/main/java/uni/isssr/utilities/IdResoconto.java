package uni.isssr.utilities;

import uni.isssr.entities.Pietanza;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by francesco on 19/06/17.
 */

@Embeddable
public class IdResoconto implements Serializable {

    @ManyToOne
    private Pietanza pietanza; // è id della pietanza

    private String data;

    public IdResoconto(){

    }

    public IdResoconto(Pietanza pietanza) {
        //this.prodottoId = prodottoId;
        this.pietanza = pietanza;
        this.data = new SimpleDateFormat("yyyy-dd-MM").format(new Date());
    }

    public Pietanza getPietanza() {
        return this.pietanza;
    }

    public void setPietanza(Pietanza pietanza) {
        this.pietanza = pietanza;
    }

    public String getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = new SimpleDateFormat("yyyyy-mm-dd").format(data);
    }
}
