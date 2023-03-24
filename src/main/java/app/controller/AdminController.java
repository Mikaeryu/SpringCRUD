package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

//это старый класс, тут только в некоторых местах добавления
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminController {
    private final String REDIRECT_TO_USERS = "redirect:/admin/users";

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "users";
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
    public String update(@ModelAttribute("user") User inboundUser, @PathVariable("id") int id) {
        var existingUser = userService.findUser(id);

        existingUser.setPassword(inboundUser.getPassword());
        existingUser.setFirstName(inboundUser.getFirstName());
        existingUser.setLastName(inboundUser.getLastName());
        existingUser.setWorkExp(inboundUser.getWorkExp());
        existingUser.setBirthDate(inboundUser.getBirthDate());

        userService.saveUser(existingUser);
        return REDIRECT_TO_USERS;
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) { //добавил тут первые три строчки, присвоение роли юзера при создании юзера
        Role roleUser = roleService.findRole("ROLE_USER");
        Set<Role> userRoleSet = user.getRoles();
        userRoleSet.add(roleUser);
        userService.saveUser(user);
        return REDIRECT_TO_USERS;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return REDIRECT_TO_USERS;
    }
}
