package uni.isssr.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Prodotto {

    @Id
    private Long id;

    private String nome;


}
