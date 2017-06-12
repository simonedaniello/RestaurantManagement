package uni.isssr.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import uni.isssr.entities.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
}
