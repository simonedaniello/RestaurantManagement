package uni.isssr.controllers;

import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.Categoria;
import uni.isssr.entities.Menu;
import uni.isssr.entities.Pietanza;

/**
 * Created by Antonio on 10/06/2017.
 */

@RestController
@CrossOrigin
public class MenuController {

    @RequestMapping(value = "/menu/{nomeMenu}", method = RequestMethod.GET)
    public String getMenu(@PathVariable String nomeMenu) {
        return null;
    }

}
