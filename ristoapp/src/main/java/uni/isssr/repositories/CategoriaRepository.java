package uni.isssr.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uni.isssr.entities.Categoria;

import java.util.List;


public interface CategoriaRepository extends JpaRepository <Categoria, Long>{

    @Transactional
    @Modifying
    @Query("select c from Categoria c inner join c.pietanze p where p.id = ?1")
    public List<Categoria> selectToDeletePietanza(Long nome);
}
