package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.Menu;
import uni.isssr.repositories.MenuRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/menu")
public class MenuEndPoint {
    @Autowired
    private MenuRepository menuRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void addTag(@RequestBody Menu menu){
        menuRepository.save(menu);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Menu> getAllMenu(){
        return menuRepository.findAll();
    }
}
