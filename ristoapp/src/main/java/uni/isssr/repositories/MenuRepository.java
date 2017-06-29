package uni.isssr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uni.isssr.entities.Menu;

import java.util.List;


public interface MenuRepository extends JpaRepository<Menu, String> {

    @Query("SELECT men FROM Menu men " +
            "JOIN men.categorie cat JOIN cat.pietanze pie JOIN pie.ingredienti ing JOIN ing.prodotto pro " +
            "WHERE pro.nome = :nomeIngrediente")
    List<Menu> findMenuByIngrediente(@Param("nomeIngrediente") String nomeIngrediente);

    @Query("SELECT men FROM Menu men " +
            "JOIN men.categorie cat JOIN cat.pietanze pie JOIN pie.etichette eti " +
            "WHERE eti.classificatore = :nomeEtichetta")
    List<Menu> findMenuByEtichetta(@Param("nomeEtichetta") String nomeEtichetta);
}
