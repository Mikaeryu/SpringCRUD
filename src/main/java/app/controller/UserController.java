package app.controller;

import app.dto.UserDto;
import app.dto.UserMapper;
import app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    @GetMapping("/user")
    public UserDto showUserInfo(Authentication auth) {
        var user = (User) auth.getPrincipal();
        return userMapper.toUserDto(user);
    }
}
