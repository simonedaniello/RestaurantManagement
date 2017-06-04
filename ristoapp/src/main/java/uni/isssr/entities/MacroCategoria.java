package uni.isssr.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class MacroCategoria extends Categoria{

    @OneToMany
    private Set<Categoria> sottocategorie;

}
