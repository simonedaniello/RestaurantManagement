package uni.isssr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.User;
import uni.isssr.repositories.UserRepository;
import uni.isssr.service.AuthService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;


    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/register/add")
    public void addNewUser (@RequestBody User user, HttpServletResponse response) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
        User found = userRepository.findOne(user.getUsername());
        if (found != null){
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Utente già nel sistema");
            return;
        }
        user.setPassword(authService.encrypt(user.getPassword()));
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/login")
    public @ResponseBody User getUser(@RequestParam String name, @RequestParam String password, HttpServletResponse response) throws IOException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        User found = userRepository.findOne(name);
        if (found == null){
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Username non registrato nel sistema");
            return null;
        }
        if (!found.getPassword().equals(authService.encrypt(password))){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Password non corretta");
            return null;
        }
        return found;
    }
}
