package uni.isssr.endPoints;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/categoriesNames")
public class CategoriesNamesEndPoint {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<String> getAllCategoriesNames(){
        String nomeCat1 = "Antipasti";
        String nomeCat2 = "Primi";
        String nomeCat3 = "Secondi";
        String nomeCat4 = "Contorni";
        String nomeCat5 = "Dolci";
        String nomeCat6 = "Frutta";
        String nomeCat7 = "Bevande";
        String nomeCat8 = "Vini Bianchi";
        String nomeCat9 = "Vini Rossi";
        String nomeCat10 = "Birre";
        String nomeCat11 = "Panini";
        String nomeCat12 = "Fritti";
        List<String> nomiCategorie = new ArrayList<String>();
        nomiCategorie.add(nomeCat1);
        nomiCategorie.add(nomeCat2);
        nomiCategorie.add(nomeCat3);
        nomiCategorie.add(nomeCat4);
        nomiCategorie.add(nomeCat5);
        nomiCategorie.add(nomeCat6);
        nomiCategorie.add(nomeCat7);
        nomiCategorie.add(nomeCat8);
        nomiCategorie.add(nomeCat9);
        nomiCategorie.add(nomeCat10);
        nomiCategorie.add(nomeCat11);
        nomiCategorie.add(nomeCat12);
        return nomiCategorie;
    }
}
