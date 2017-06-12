package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.Menu;

/**
 * Created by alberto on 05/06/17.
 */
public interface MenuRepository extends JpaRepository<Menu, String>{
}
