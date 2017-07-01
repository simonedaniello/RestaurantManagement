package uni.isssr.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.ComandaItem;

public interface ComandaItemRepository extends JpaRepository<ComandaItem, Long> {
}
