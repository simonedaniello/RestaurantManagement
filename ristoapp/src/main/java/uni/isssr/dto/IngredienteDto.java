package uni.isssr.dto;

/**
 * Created by francesco on 19/06/17.
 */
public class IngredienteDto {

    private String nome;

    private Double quantita;

    private Long prodottoId;

    public IngredienteDto(){
    }

    public IngredienteDto(String nomeProdotto, double quantita, long prodottoId) {
        this.nome = nomeProdotto;
        this.quantita = new Double(quantita);
        this.prodottoId = new Long(prodottoId);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeProdotto) {
        this.nome = nomeProdotto;
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
