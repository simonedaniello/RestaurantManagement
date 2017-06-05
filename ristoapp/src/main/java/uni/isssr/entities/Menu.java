package uni.isssr.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Menu {

    @Id
    private String nome;

    private String descrizione;

    @OneToMany
    private List<Categoria> categorie;
}
