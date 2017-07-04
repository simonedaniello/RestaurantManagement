package uni.isssr.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private Integer posizione;

    public Integer getPosizione() {
        return posizione;
    }

    public void setPosizione(Integer posizione) {
        this.posizione = posizione;
    }

    @ManyToMany
    private List<Pietanza> pietanze;

    public Categoria() {

    }


    public Categoria(String nome) {
        this.nome = nome;
        this.pietanze = new ArrayList<>();
    }

    public Categoria(String nome, List<Pietanza> pietanze) {
        this.nome = nome;
        this.pietanze = pietanze;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pietanza> getPietanze() {
        return pietanze;
    }

    public void setPietanze(List<Pietanza> pietanze) {
        this.pietanze = pietanze;
    }

    public void addPietanza(Pietanza pietanza) {
        this.pietanze.add(pietanza);
    }

    public void removePietanza(Pietanza pietanza) {
        this.pietanze.remove(pietanza);
    }
}
