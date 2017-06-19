package uni.isssr.dto;

/**
 * Created by francesco on 19/06/17.
 */
public class ResocontoPietanzaDto {

    private Long value;

    private Long prodottoId;

    public ResocontoPietanzaDto(){
    }

    public ResocontoPietanzaDto(Long value, Long prodottoId) {
        this.value = value;
        this.prodottoId = prodottoId;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(Long prodottoId) {
        this.prodottoId = prodottoId;
    }
}
