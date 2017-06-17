package uni.isssr.dto;

/**
 * Created by francesco on 17/06/17.
 */
public class EtichettaDto {

    private String classificatore;

    public EtichettaDto(){

    }

    public EtichettaDto(String classificatore){
        this.classificatore = classificatore;
    }

    public String getClassificatore() {
        return this.classificatore;
    }
}
