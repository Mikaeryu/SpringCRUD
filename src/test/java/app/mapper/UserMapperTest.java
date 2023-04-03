package app.mapper;

import static org.hamcrest.MatcherAssert.*;

import app.model.Role;
import app.model.User;
import org.junit.jupiter.api.Assertions;
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

    private final User USER_WITHOUT_ROLES = User.builder()
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
    void AllFieldsAreEqual_whenUserWithoutRoles_isMappingToUserDto() {
        var userDto = userMapper.toUserDto(USER_WITHOUT_ROLES);
        Assertions.assertEquals(userDto.getId(),  USER_WITHOUT_ROLES.getId());
        Assertions.assertEquals(userDto.getPassword(),  USER_WITHOUT_ROLES.getPassword());
        Assertions.assertEquals(userDto.getLogin(),  USER_WITHOUT_ROLES.getLogin());
        Assertions.assertEquals(userDto.getWorkExp(),  USER_WITHOUT_ROLES.getWorkExp());
        Assertions.assertEquals(userDto.getRoles(),  USER_WITHOUT_ROLES.getRoles());
        Assertions.assertEquals(userDto.getBirthDate(),  USER_WITHOUT_ROLES.getBirthDate());
        Assertions.assertEquals(userDto.getLastName(),  USER_WITHOUT_ROLES.getLastName());
        Assertions.assertEquals(userDto.getFirstName(),  USER_WITHOUT_ROLES.getFirstName());
    }

}
