package uni.isssr.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
}
