package uni.isssr.controller;

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
import uni.isssr.dto.PietanzaMenuDto;
import uni.isssr.endPoints.PietanzaEndPoint;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.PietanzaRepository;
import uni.isssr.service.PietanzaService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PietanzaEndPointTest {

	private MockMvc mockMvc;

	@Mock
    private PietanzaRepository pietanzaRepository;

	@InjectMocks
	private PietanzaEndPoint pietanzaController;

	@InjectMocks
    @Mock
    private PietanzaService pietanzaService;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(pietanzaController).build();
	}

	@Test
	public void getAllPietanzeTest() throws Exception {


		Pietanza pietanza1 = new Pietanza("Pasta al sugo", 12.00);
		Pietanza pietanza2 = new Pietanza("Petto di pollo", 13.00);
		List<Pietanza> pietanze = Arrays.asList(pietanza1, pietanza2);
		when(pietanzaRepository.findAll()).thenReturn(pietanze);



		this.mockMvc.perform(get("/dish/getAll"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].pietanzaDto.nome", is("Pasta al sugo")))
				.andExpect(jsonPath("$[1].pietanzaDto.nome", is("Petto di pollo")));


		verify(pietanzaService, times(1)).findAll();

	}

}
