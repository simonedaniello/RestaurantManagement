package uni.isssr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isssr.entities.Categoria;
import uni.isssr.entities.CategoriaRepository;
import uni.isssr.entities.Menu;
import uni.isssr.entities.MenuRepository;

/**
 * Created by alberto on 08/06/17.
 */

@RestController
@CrossOrigin
@RequestMapping(path = "/generate")
public class Prova {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public void populateMenu(){
        /*
        Menu menu1 = new Menu("bbbbb", "hbwhkebckjc", "jnjecn", "jnlejcnj");
        Menu menu2 = new Menu("aaaaa", "hbwhkebckjc", "jnjecn", "jnlejcnj");
        Categoria categoria = new Categoria("a");
        Categoria categoria1 = new Categoria("b");
        categoriaRepository.save(categoria);
        categoriaRepository.save(categoria1);
        menu1.addCategoria(categoria);
        menu1.addCategoria(categoria1);
        menuRepository.save(menu1);
        menuRepository.save(menu2);
        */
    }
}
