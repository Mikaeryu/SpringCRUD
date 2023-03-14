package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String index(Model model) {
        userService.saveUser(new User()); //ОПЕРАЦИЯ ДВОИТСЯ если по умолчанию запускать эту страницу
        model.addAttribute("users", userService.getUserList());
        return "index";
    }
}
