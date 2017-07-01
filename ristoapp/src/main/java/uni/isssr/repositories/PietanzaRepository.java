package uni.isssr.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.Etichetta;
import uni.isssr.entities.Pietanza;


public interface PietanzaRepository extends JpaRepository<Pietanza, Long>{
    Page<Pietanza> findAllByNomeContainingOrderByNome(String nome, Pageable pageable);
    Page<Pietanza> findDistinctByNomeContainingAndEtichetteIn(String nome, Etichetta[] etichette, Pageable pageable);


    /* Per cercare una pietanza per nome */
    Pietanza findByNome(String nome);
}
