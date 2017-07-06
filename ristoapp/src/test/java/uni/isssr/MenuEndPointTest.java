package uni.isssr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uni.isssr.dto.MenuItemDto;
import uni.isssr.dto.MenuSearchDto;
import uni.isssr.dto.PietanzaMenuDto;
import uni.isssr.endPoints.MenuEndPoint;
import uni.isssr.endPoints.PietanzaEndPoint;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.service.MenuService;
import uni.isssr.service.PietanzaService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Antonio on 06/07/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuEndPointTest {

    private MockMvc mockMvc;

    @Mock
    private MenuService menuService;

    @InjectMocks
    private MenuEndPoint menuController;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @Test
    public void getMenuTest() throws Exception {

        MenuSearchDto dto1 = new MenuSearchDto("menu estivo", null, null, null);
        MenuSearchDto dto2 = new MenuSearchDto("menu invernale", null, null, null);
        List<MenuSearchDto> listDto = Arrays.asList(dto1, dto2);
        when(menuService.searchAllMenu()).thenReturn(listDto);

        this.mockMvc.perform(get("/menu/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is("menu estiv")))
                .andExpect(jsonPath("$[1].nome", is("menu invernale")));


        List<MenuSearchDto> list = verify(menuService, times(1)).searchAllMenu();
        verifyNoMoreInteractions(menuService);
    }
}
