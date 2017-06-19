package uni.isssr.utilities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by francesco on 19/06/17.
 */

@Embeddable
public class IdResoconto implements Serializable {

    private Long prodottoId;

    private String data;

    public IdResoconto(){

    }

    public IdResoconto(Long prodottoId) {
        this.prodottoId = prodottoId;
        this.data = new SimpleDateFormat("yyyy-dd-MM").format(new Date());
    }

    public Long getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(Long prodottoId) {
        this.prodottoId = prodottoId;
    }

    public String getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = new SimpleDateFormat("yyyyy-mm-dd").format(data);
    }
}
