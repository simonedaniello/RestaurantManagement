package uni.isssr.entities;

import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
public abstract class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    protected String nome;
}
