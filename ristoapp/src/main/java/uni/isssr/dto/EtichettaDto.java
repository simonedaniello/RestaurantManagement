package uni.isssr.dto;


public class EtichettaDto {

    private String classificatore;

    public EtichettaDto(String classificatore) {
        this.classificatore = classificatore;
    }

    public EtichettaDto() {}

    public String getClassificatore() {
        return classificatore;
    }

    public void setClassificatore(String classificatore) {
        this.classificatore = classificatore;
    }
}
