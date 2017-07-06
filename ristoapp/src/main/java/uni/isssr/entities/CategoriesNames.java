package uni.isssr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by alberto on 06/07/17.
 */

@Entity
public class CategoriesNames {

    @Id
    private String nome;

    public String getNome() {
        return nome;
    }

    public CategoriesNames() {
    }

    public CategoriesNames(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
