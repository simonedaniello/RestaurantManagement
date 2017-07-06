package uni.isssr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uni.isssr.dto.PietanzaMenuDto;
import uni.isssr.endPoints.PietanzaEndPoint;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.service.PietanzaService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Antonio on 06/07/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PietanzaServiceTest {

    /*
        Test del service PietanzaService
     */

    @Mock
    private PietanzaRepository pietanzaRepository;

    @InjectMocks
    private PietanzaService pietanzaService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    /*
        Test del metodo findAll()
     */

    @Test
    public void findAllTest() {

        Pietanza pietanza1 = new Pietanza("Pasta al sugo", 12.00);
        Pietanza pietanza2 = new Pietanza("Petto di pollo", 13.00);
        List<Pietanza> pietanze = Arrays.asList(pietanza1, pietanza2);
        when(pietanzaRepository.findAll()).thenReturn(pietanze);

        List<PietanzaMenuDto> pietanzeDtos = pietanzaService.findAll();
        Assert.assertEquals(pietanza1.getNome(), pietanzeDtos.get(0).getPietanzaDto().getNome());
        Assert.assertEquals(pietanza2.getNome(), pietanzeDtos.get(1).getPietanzaDto().getNome());
    }

}
