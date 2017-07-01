package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.MenuItemDto;
import uni.isssr.dto.MenuSearchDto;
import uni.isssr.entities.Categoria;
import uni.isssr.entities.Menu;
import uni.isssr.entities.Pietanza;
import uni.isssr.service.MenuService;

import java.util.List;

/**
 * Created by Antonio on 30/06/2017.
 */

@RestController
@CrossOrigin
public class MenuEndPoint {

    @Autowired
    private MenuService menuService;


    @RequestMapping(value = "/menu/{nomeMenu}", method = RequestMethod.GET)
    public MenuItemDto getMenu(@PathVariable String nomeMenu) {
        return menuService.searchMenu(nomeMenu);
    }

    @RequestMapping(value = "/menu/nome/{nomeMenu}", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenuByName(@PathVariable String nomeMenu) {
        return menuService.searchMenuByName(nomeMenu);
    }

    @RequestMapping(value = "/menu/ingrediente/{nomeIngrediente}", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenuByIngrediente(@PathVariable String nomeIngrediente) {
        return menuService.searchMenuByIngrediente(nomeIngrediente);
    }

    @RequestMapping(value = "/menu/etichetta/{nomeEtichetta}", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenuByEtichetta(@PathVariable String nomeEtichetta) {
        return menuService.searchMenuByEtichetta(nomeEtichetta);
    }

    @RequestMapping(value = "/menu/findAll", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenu() {
        return menuService.searchAllMenu();
    }


}
