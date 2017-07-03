package uni.isssr.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uni.isssr.entities.Etichetta;
import uni.isssr.entities.Pietanza;

import java.util.List;


public interface PietanzaRepository extends JpaRepository<Pietanza, Long>{
    Page<Pietanza> findAllByNomeContainingOrderByNome(String nome, Pageable pageable);
    Page<Pietanza> findDistinctByNomeContainingAndEtichetteIn(String nome, Etichetta[] etichette, Pageable pageable);

    //prendo le pietanze di cui devo fare update dell'etichetta
    @Query(" select p from Pietanza p where ?1 in  " +
            "(select e from Pietanza d join d.etichette e where d.id = p.id)")
    List<Pietanza> findToUpdate(String etichetta);

    /* Per cercare una pietanza per nome */
    Pietanza findByNome(String nome);
}
