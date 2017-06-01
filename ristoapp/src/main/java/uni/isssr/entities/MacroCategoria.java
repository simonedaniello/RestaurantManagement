package uni.isssr.entities;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class MacroCategoria extends Categoria{

    private Set<Categoria> sottocategorie;

}
