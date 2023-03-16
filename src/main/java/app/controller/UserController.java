package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final String REDIRECT_TO_USERS = "redirect:/users";

    @Autowired
    UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        var user = userService.findUser(id);
        model.addAttribute("user", user);
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute(userService.findUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return REDIRECT_TO_USERS;
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return REDIRECT_TO_USERS;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return REDIRECT_TO_USERS;
    }
}
