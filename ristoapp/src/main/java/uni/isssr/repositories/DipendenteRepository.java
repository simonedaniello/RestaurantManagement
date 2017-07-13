package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uni.isssr.entities.Dipendente;

/**
 * Created by Simone on 13/07/2017.
 */
public interface DipendenteRepository extends JpaRepository<Dipendente, String> {
    @Transactional
    @Modifying
    @Query("delete from Dipendente where cognome = ?1")
    void deleteDipendente(String cognome);
}
