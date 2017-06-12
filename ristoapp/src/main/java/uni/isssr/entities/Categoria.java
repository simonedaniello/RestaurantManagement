package uni.isssr.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pietanza> pientanze;

    public Categoria() {

    }


    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(List<Pietanza> pietanze, String nome) {
        this.nome = nome;
        this.pientanze = pietanze;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pietanza> getPientanze() {
        return pientanze;
    }

    public void setPientanze(List<Pietanza> pientanze) {
        this.pientanze = pientanze;
    }

    public void addPietanza(Pietanza pietanza) {
        this.pientanze.add(pietanza);
    }

    public void removePietanza(Pietanza pietanza) {
        this.pientanze.remove(pietanza);
    }
}
