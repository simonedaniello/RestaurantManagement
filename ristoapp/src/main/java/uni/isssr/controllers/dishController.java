package uni.isssr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uni.isssr.entities.Pietanza;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francesco on 03/06/17.
 */
@Controller
public class dishController {

    @RequestMapping(path = "dishes", method  = RequestMethod.GET)
    @ResponseBody public List<Pietanza> getDishes() {
        String[] records = {
        "Bruschetta al pomodoro",
                "Moscardini fritti",
                "Risotto alla pescatora",
                "Spaghetti alle vongole", "Spaghetti allo scoglio",
                "Frittura di paranza"
        };
        List<Pietanza> dishes = new ArrayList<Pietanza>();
        for(int j = 0; j < records.length; j++) {
            String nome = records[j];
            dishes.add(new Pietanza(null, nome, null));
        }
        return dishes;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getCategoriaFile() {
        return "categoria.html";
    }
}
