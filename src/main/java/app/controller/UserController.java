package app.controller;

import app.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String showInfo(Model model, Authentication auth) {
        var user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        return "user_info";
    }
}
