package uni.isssr.endPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.CategoriesNames;
import uni.isssr.repositories.CategoriesNamesRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/categoriesNames")
public class CategoriesNamesEndPoint {

    @Autowired
    private CategoriesNamesRepository categoriesNamesRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<CategoriesNames> getAllCategoriesNames(){
        return categoriesNamesRepository.findAll();
    }
}
