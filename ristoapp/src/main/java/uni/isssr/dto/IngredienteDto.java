package uni.isssr.dto;

/**
 * Created by francesco on 19/06/17.
 */
public class IngredienteDto {

    private String nomeProdotto;

    private Double quantita;

    private Long prodottoId;

    public IngredienteDto(){
    }

    public IngredienteDto(String nomeProdotto, double quantita, long prodottoId) {
        this.nomeProdotto = nomeProdotto;
        this.quantita = new Double(quantita);
        this.prodottoId = new Long(prodottoId);
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public double getQuantita() {
        return quantita.doubleValue();
    }

    public void setQuantita(double quantita) {
        this.quantita = new Double(quantita);
    }

    public Long getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(Long prodottoId) {
        this.prodottoId = prodottoId;
    }
}
