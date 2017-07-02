package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uni.isssr.entities.ComandaItem;
import uni.isssr.entities.ComandaOrder;

import java.util.List;

/**
 * Created by Antonio on 30/06/2017.
 */
public interface ComandaOrderRepository extends JpaRepository<ComandaOrder, Long> {

    @Query("SELECT c FROM ComandaOrder c WHERE c.tavolo = :tavolo AND c.active = :stato")
    ComandaOrder findComandaOrder(@Param("tavolo") Integer tavolo, @Param("stato") Boolean stato);

    @Query("SELECT c.tavolo FROM ComandaOrder c WHERE c.active = :stato")
    List<Integer> findComandeAttive(@Param("stato") Boolean stato);
}
