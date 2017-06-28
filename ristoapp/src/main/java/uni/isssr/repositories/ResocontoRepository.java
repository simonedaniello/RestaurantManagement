package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uni.isssr.dto.ResocontoAnalyticsDto;
import uni.isssr.entities.Categoria;
import uni.isssr.entities.ResocontoPietanza;
import uni.isssr.utilities.IdResoconto;

import java.util.Date;
import java.util.List;

/**
 * Created by francesco on 19/06/17.
 */
public interface ResocontoRepository extends JpaRepository<ResocontoPietanza, IdResoconto> {

    /*@Transactional
    @Query("select all " +
            "from Menu as M join M.categorie as C join C.pietanze as P join Resoconto as R")
    public List<String> getResocontiByData(String data);*/
    /*,sum(R.venduto),sum(R.preparato)
     +
            + "group by C.nome +
            " where M.isActive = true and M.categorie = C.id and C.pietanze = P.id and R.prodottoId = P.id" +
            " and R.data = ?1 ""
    * */
}
