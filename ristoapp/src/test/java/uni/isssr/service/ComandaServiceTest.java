package uni.isssr.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import uni.isssr.dto.ComandaItemDto;
import uni.isssr.dto.ComandaOrderDto;
import uni.isssr.entities.ComandaItem;
import uni.isssr.entities.ComandaOrder;
import uni.isssr.repositories.ComandaOrderRepository;

import java.util.Arrays;


/**
 * Created by Antonio on 06/07/2017.
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ComandaServiceTest {

    private static final double DELTA = 1e-15;

    @Autowired
    private ComandaOrderRepository comandaOrderRepository;

    @Autowired
    private ComandaService comandaService;

    @Before
    public void init(){

        ComandaItem comandaItem = new ComandaItem();
        comandaItem.setNomePietanza("Pasta al pomodoro");
        comandaItem.setQuantita(1);
        comandaItem.setPrezzoPietanza(12.00);
        ComandaOrder comandaOrder = new ComandaOrder();
        comandaOrder.setActive(true);
        comandaOrder.setTavolo(1);
        comandaOrder.setComandaItems(Arrays.asList(comandaItem));
        comandaOrderRepository.save(comandaOrder);

    }

    /*
        Test di inserimento di una nuova comanda allo stesso tavolo (attivo) con controllo di consistenza
        per verificare l'incremento della quantità per lo stesso prodotto
     */

    @Test
    public void insertComandaTest() {

        ComandaOrderDto comandaOrderDto = new ComandaOrderDto();
        ComandaItemDto comandaItemDto = new ComandaItemDto();
        comandaItemDto.setPietanza("Pasta al pomodoro");
        comandaItemDto.setQuantita(1);
        comandaItemDto.setPrezzo(12.00);
        comandaOrderDto.setComandaItems(Arrays.asList(comandaItemDto));
        comandaOrderDto.setTavolo(1);

        comandaService.insertComanda(comandaOrderDto);

        ComandaOrder comandaOrderDb = comandaOrderRepository.findComandaOrder(1, true);
        Assert.assertNotEquals(null, comandaOrderDb);
        Assert.assertEquals(2, comandaOrderDb.getComandaItems().get(0).getQuantita());
        Assert.assertEquals(12.00, comandaOrderDb.getComandaItems().get(0).getPrezzoPietanza(), DELTA);
    }

}
