package uni.isssr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uni.isssr.entities.User;
import uni.isssr.entities.UserRepository;

@RestController
@CrossOrigin
@RequestMapping(path="/register")
public class RegisterController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;


    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/add")
    public void addNewUser (@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
