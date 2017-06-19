package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.Prodotto;

/**
 * Created by francesco on 19/06/17.
 */
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

}
