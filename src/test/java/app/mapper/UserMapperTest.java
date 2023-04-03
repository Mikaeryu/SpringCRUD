package app.mapper;

import app.model.Role;
import app.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    private final User USER_WITHOUT_ROLE = User.builder()
            .id(1)
            .login("user1")
            .password("pass")
            .firstName("User1")
            .lastName("Userovich1")
            .workExp(3)
            .birthDate(LocalDate.EPOCH)
            .build();

    private final User USER_WITH_ALL_PARAMS = User.builder()
            .id(2)
            .login("user2")
            .password("pass")
            .firstName("User2")
            .lastName("Userovich2")
            .workExp(3)
            .birthDate(LocalDate.MAX)
            .roles(new HashSet<>(List.of(new Role(1L, "ROLE_USER"))))
            .build();

    @Test

}
