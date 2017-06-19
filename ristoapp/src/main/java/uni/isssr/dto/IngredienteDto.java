package uni.isssr.dto;

/**
 * Created by francesco on 19/06/17.
 */
public class IngredienteDto {

    private String nomeProdotto;

    private Double quantita;

    public IngredienteDto(){
    }

    public IngredienteDto(String nomeProdotto, double quantita) {
        this.nomeProdotto = nomeProdotto;
        this.quantita = new Double(quantita);
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
}
