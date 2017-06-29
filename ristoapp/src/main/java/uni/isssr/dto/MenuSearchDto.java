package uni.isssr.dto;

import java.util.Date;

/**
 * Created by Antonio on 29/06/2017.
 */
public class MenuSearchDto {

    private String nome;
    private String descrizione;
    private String immagine;
    private String data;

    public MenuSearchDto() {}

    public MenuSearchDto(String nome, String descrizione, String immagine, String data) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
