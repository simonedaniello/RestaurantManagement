package uni.isssr.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
}
