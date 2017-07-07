package uni.isssr.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import uni.isssr.entities.*;
import uni.isssr.repositories.ComandaOrderRepository;
import uni.isssr.repositories.MenuRepository;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.repositories.ProdottoRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Antonio on 05/07/2017.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ComandaOrderRepositoryTest {

    @Autowired
    private ComandaOrderRepository comandaOrderRepository;

    private static boolean initIsDone = false;

    @Before
    public void initComandaOrder() {

        if (initIsDone)
            return;

        ComandaItem comandaItem1 = new ComandaItem();
        comandaItem1.setNomePietanza("Pasta al sugo");
        comandaItem1.setQuantita(1);
        comandaItem1.setPrezzoPietanza(10.00);

        ComandaItem comandaItem2 = new ComandaItem();
        comandaItem2.setNomePietanza("Cotoletta di pollo");
        comandaItem2.setQuantita(2);
        comandaItem2.setPrezzoPietanza(12.00);

        List<ComandaItem> comandaItems = Arrays.asList(comandaItem1, comandaItem2);

        ComandaOrder comandaOrder = new ComandaOrder();
        comandaOrder.setComandaItems(comandaItems);
        comandaOrder.setActive(true);
        comandaOrder.setTavolo(1);

        comandaOrderRepository.save(comandaOrder);

        initIsDone = true;
    }

    @Test
    public void findComandaOrderTest() {
        ComandaOrder comandaOrder = comandaOrderRepository.findComandaOrder(1, true);
        Assert.assertNotEquals(null, comandaOrder); // Esiste una comanda attiva associata al tavolo
        Assert.assertEquals("Cotoletta di pollo", comandaOrder.getComandaItems().get(1).getNomePietanza());
    }

}

