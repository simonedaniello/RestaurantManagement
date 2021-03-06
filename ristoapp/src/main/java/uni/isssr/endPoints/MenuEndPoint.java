package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.dto.CategoriaMenuDto;
import uni.isssr.dto.MenuDto;
import uni.isssr.dto.MenuItemDto;
import uni.isssr.dto.MenuSearchDto;
import uni.isssr.entities.Categoria;
import uni.isssr.entities.Menu;
import uni.isssr.entities.Pietanza;
import uni.isssr.repositories.MenuRepository;
import uni.isssr.service.MenuService;

import java.util.List;

/**
 * Created by Antonio on 30/06/2017.
 */

@RestController
@CrossOrigin
@RequestMapping(path = "/menu")
public class MenuEndPoint {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/attivo", method = RequestMethod.GET)
    public List<CategoriaMenuDto> getCategorieMenuAttivo() {
        return menuService.searchMenuAttivo();
    }

    @RequestMapping(value = "/{nomeMenu}", method = RequestMethod.GET)
    public MenuItemDto getMenu(@PathVariable String nomeMenu) {
        return menuService.searchMenu(nomeMenu);
    }

    @RequestMapping(value = "/nome/{nomeMenu}", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenuByName(@PathVariable String nomeMenu) {
        return menuService.searchMenuByName(nomeMenu);
    }

    @RequestMapping(value = "/ingrediente/{nomeIngrediente}", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenuByIngrediente(@PathVariable String nomeIngrediente) {
        return menuService.searchMenuByIngrediente(nomeIngrediente);
    }

    @RequestMapping(value = "/etichetta/{nomeEtichetta}", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenuByEtichetta(@PathVariable String nomeEtichetta) {
        return menuService.searchMenuByEtichetta(nomeEtichetta);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenu() {
        return menuService.searchAllMenu();
    }

    @RequestMapping(value = "/findMenuAttivo", method = RequestMethod.GET)
    public List<MenuSearchDto> getMenuAttivo() {
        return menuService.getMenuAttivo();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveMenu(@RequestBody MenuDto menuDto) {
        menuService.saveMenu(menuDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateMenu(@RequestBody MenuDto menuDto) {
        menuService.saveMenu(menuDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ID}")
    public void deletePietanza(@PathVariable(value = "ID") String id){
        menuService.deleteMenu(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ID}")
    public void attivaMenu(@PathVariable(value = "ID") String id){
        menuService.attivaMenu(id);
    }


}
