package app.controller;

import app.dto.UserDto;
import app.dto.UserMapper;
import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//это старый класс, тут только в некоторых местах добавления
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminController {
    private final String REDIRECT_TO_USERS = "redirect:/admin/users";

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @GetMapping
    public String users(Model model) {
        List<UserDto> userDtoList = userService.getUserList()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());

        model.addAttribute("users", userDtoList);
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
    public String update(@ModelAttribute("user") UserDto inboundUserDto, @PathVariable("id") int id) {
        var existingUser = userService.findUser(id);

        userMapper.updateUserFromDto(inboundUserDto, existingUser);

        userService.saveUser(existingUser);
        return REDIRECT_TO_USERS;
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") UserDto userDto) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") UserDto userDto) { //добавил тут первые три строчки, присвоение роли юзера при создании юзера
        Role roleUser = roleService.findRole("ROLE_USER");
        Set<Role> userRoleSet = userDto.getRoles();
        userRoleSet.add(roleUser);

        User user = userMapper.toUser(userDto);
        userService.saveUser(user);
        return REDIRECT_TO_USERS;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return REDIRECT_TO_USERS;
    }
}
