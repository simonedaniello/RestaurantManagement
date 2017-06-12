package uni.isssr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.User;
import uni.isssr.repositories.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alberto on 06/06/17.
 */

@RestController
@CrossOrigin
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public @ResponseBody User getUser(@RequestParam String name, @RequestParam String password, HttpServletResponse response) throws IOException {
        User found = userRepository.findOne(name);
        if (found == null){
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Username not found");
            return null;
        }
        if (!found.getPassword().equals(password)){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Password not found");
            return null;
        }
        return found;
    }
}
