package uni.isssr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import uni.isssr.entities.CategoriesNames;
import uni.isssr.repositories.CategoriesNamesRepository;

/**
 * Created by alberto on 06/07/17.
 */

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{

    @Autowired
    private CategoriesNamesRepository categoriesNamesRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String[] array = {"Antipasti", "Primi", "Secondi", "Contorni", "Dolci", "Frutta", "Bevande", "Vini Bianchi", "Vini Rossi", "Birre", "Panini", "Fritti", "Pizze"};
        for (int i = 0; i < array.length; i++){
            CategoriesNames temp = new CategoriesNames(array[i]);
            categoriesNamesRepository.save(temp);
        }
    }
}
