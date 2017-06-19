package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.ResocontoPietanza;
import uni.isssr.utilities.IdResoconto;

import java.util.Date;

/**
 * Created by francesco on 19/06/17.
 */
public interface ResocontoRepository extends JpaRepository<ResocontoPietanza, IdResoconto> {
}
