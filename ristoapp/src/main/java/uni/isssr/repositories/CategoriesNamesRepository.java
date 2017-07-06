package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.CategoriesNames;

/**
 * Created by alberto on 06/07/17.
 */
public interface CategoriesNamesRepository extends JpaRepository<CategoriesNames, String> {
}
