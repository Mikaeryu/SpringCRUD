package app.controller;

import app.dto.UserDto;
import app.mapper.UserMapper;
import app.model.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminController {

    private final UserService userService;
    private final UserMapper mapper;

    @GetMapping
    public List<UserDto> users() {
        return userService.getUserList()
                .stream()
                .map(mapper::toUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto showOne(@PathVariable long id) {
        var user = userService.findUser(id);
        return mapper.toUserDto(user);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        User user = mapper.toUser(userDto);
        userService.saveUser(user);
        return userDto;
    }

    @PutMapping("/{id}")
    public UserDto update(@RequestBody UserDto inboundUserDto, @PathVariable long id) {
        var existingUser = userService.findUser(id);
        mapper.updateUserFromDto(inboundUserDto, existingUser);
        userService.saveUser(existingUser);
        return inboundUserDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
