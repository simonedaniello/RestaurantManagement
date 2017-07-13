package uni.isssr.dto;


public class DipendenteDto {

    private String nome;
    private String cognome;
    private String ruolo;

    public DipendenteDto(String nome, String cognome, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public DipendenteDto() {}

    public DipendenteDto getDipendente() {
        return this;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }
}
