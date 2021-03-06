package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uni.isssr.entities.Categoria;
import uni.isssr.entities.Pietanza;
import uni.isssr.entities.ResocontoPietanza;
import uni.isssr.utilities.IdResoconto;

import java.util.Date;
import java.util.List;

/**
 * Created by francesco on 19/06/17.
 */
public interface ResocontoRepository extends JpaRepository<ResocontoPietanza, IdResoconto> {

    @Transactional
    @Query("select p from ResocontoPietanza r inner join r.id.pietanza p")
    public List<Pietanza> getPietanzaByData();

    /*,sum(R.venduto),sum(R.preparato)
     +
            + "group by C.nome +
            " where M.isActive = true and M.categorie = C.id and C.pietanze = P.id and R.prodottoId = P.id" +
            " and R.data = ?1 ""
    * */

    @Transactional
    @Query("select r from ResocontoPietanza r where r.id.data = ?1")
    public List<ResocontoPietanza> findAllByData(String data);
}
