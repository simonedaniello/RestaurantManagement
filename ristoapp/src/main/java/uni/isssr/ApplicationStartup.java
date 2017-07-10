package uni.isssr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import uni.isssr.entities.CategoriesNames;
import uni.isssr.entities.User;
import uni.isssr.repositories.CategoriesNamesRepository;
import uni.isssr.repositories.UserRepository;
import uni.isssr.service.AuthService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alberto on 06/07/17.
 */

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{

    @Autowired
    private CategoriesNamesRepository categoriesNamesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String[] array = {"Antipasti", "Primi", "Secondi", "Contorni", "Dolci", "Frutta", "Bevande", "Vini Bianchi", "Vini Rossi", "Birre", "Panini", "Fritti", "Pizze"};
        for (int i = 0; i < array.length; i++){
            CategoriesNames temp = new CategoriesNames(array[i]);
            categoriesNamesRepository.save(temp);
        }
        User admin = new User();
        admin.setUsername("admin");
        try {
            admin.setPassword(authService.encrypt("admin"));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        admin.setType("admin");
        admin.setName("amministratore");
        admin.setEmail("admin@admin");
        userRepository.save(admin);
    }
}
