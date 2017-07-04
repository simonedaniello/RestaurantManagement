package uni.isssr.dto;

/**
 * Created by Antonio on 30/06/2017.
 */
public class ComandaItemDto {

    private String pietanza;
    private int quantita;
    private double prezzo;
    private int id;


    public ComandaItemDto() {}

    public String getPietanza() {
        return pietanza;
    }

    public void setPietanza(String pietanza) {
        this.pietanza = pietanza;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
