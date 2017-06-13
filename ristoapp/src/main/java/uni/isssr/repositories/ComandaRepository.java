package uni.isssr.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.Comanda;

public interface ComandaRepository extends JpaRepository<Comanda, Long>{
}
