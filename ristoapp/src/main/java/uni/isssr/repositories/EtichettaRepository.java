package uni.isssr.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uni.isssr.entities.Etichetta;

public interface EtichettaRepository extends JpaRepository<Etichetta, String>{

    @Transactional
    @Modifying
    @Query("delete from Etichetta where classificatore = ?1")
    public void deleteEtichetta(String classificatore);

    /*@Transactional
    @Modifying
    @Query("update Etichetta set classificatore = ?1 where classificatore = ?2")
    public void updateEtichetta(String newClassificatore, String oldClassificatore);*/

    /*@Transactional
    @Modifying
    @Query("update Pietanza set etichette.classificatore = ?1 where etichette.classificatore = ?2")
    public void updateEtichettaPietanza(String classificatore, String oldClassificatore);*/
}
