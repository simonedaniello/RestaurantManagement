package uni.isssr.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import uni.isssr.entities.*;
import uni.isssr.repositories.MenuRepository;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.repositories.ProdottoRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Antonio on 30/06/2017.
 */


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private PietanzaRepository pietanzaRepository;

    private static boolean initIsDone = false;

    @Before
    public void initMenu() {

        if (initIsDone)
            return;

        Prodotto pd1 = new Prodotto(1L, "cozze");
        Prodotto pd2 = new Prodotto(2L, "vongole");
        Prodotto pd3 = new Prodotto(3L, "riso");

        prodottoRepository.save(pd1);
        prodottoRepository.save(pd2);
        prodottoRepository.save(pd3);

        Ingrediente i1 = new Ingrediente(pd1, 10);
        Ingrediente i2 = new Ingrediente(pd2, 20);
        Ingrediente i3 = new Ingrediente(pd3, 100);
        List<Ingrediente> ingredienti = Arrays.asList(i1, i2, i3);

        Etichetta e1 = new Etichetta("piccante");
        Etichetta e2 = new Etichetta("celiaco");

        Pietanza p1 = new Pietanza("risotto alla pescatora", 10.00);

        p1.addEtichetta(e1);
        p1.addEtichetta(e2);
        p1.setIngredienti(ingredienti);

        pietanzaRepository.save(p1);

        Categoria categoria1 = new Categoria("Primi");
        categoria1.addPietanza(p1);
        Menu menu1 = new Menu("Menu estivo", "Sapori delicati");
        menu1.addCategoria(categoria1);
        menu1.setIsActive(true);

        menuRepository.save(menu1);

        initIsDone = true;
    }

    @Test
    public void findByNameTest() {
        Menu menu = menuRepository.findOne("Menu estivo");
        Assert.assertEquals("Menu estivo", menu.getNome()); // Uguale nome
    }

    @Test
    public void findByIngredienteTest() {
        List<Menu> menu = menuRepository.findMenuByIngrediente("riso");
        Assert.assertEquals(1, menu.size()); // Un solo menu con l'ingrediente specificato
        Assert.assertEquals("Menu estivo", menu.get(0).getNome());
    }

    @Test
    public void findByEtichettaTest() {
        List<Menu> menu = menuRepository.findMenuByEtichetta("celiaco");
        Assert.assertEquals(1, menu.size()); // Un solo menu con l'ingrediente specificato
        Assert.assertEquals("Menu estivo", menu.get(0).getNome());
    }

    @Test
    public void findActiveTest() {
        Menu menu = menuRepository.findOneByIsActive(true);
        Assert.assertNotEquals(null, menu);
        Assert.assertEquals("Menu estivo", menu.getNome());
    }

}
