package uni.isssr.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class CategoriaFinale extends Categoria{

    @OneToMany
    private Set<Pietanza> pientanze;
}
