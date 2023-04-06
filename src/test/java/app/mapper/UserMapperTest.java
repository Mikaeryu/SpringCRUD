package app.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import app.model.Role;
import app.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    private final static User USER_WITHOUT_ROLES = User.builder()
            .id(1)
            .login("user1")
            .password("pass")
            .firstName("User1")
            .lastName("Userovich1")
            .workExp(3)
            .birthDate(LocalDate.EPOCH)
            .roles(new HashSet<>())
            .build();

    private final static User USER_WITH_ALL_FIELDS = User.builder()
            .id(2)
            .login("user2")
            .password("pass")
            .firstName("User2")
            .lastName("Userovich2")
            .workExp(3)
            .birthDate(LocalDate.MAX)
            .roles(new HashSet<>(List.of(new Role(1L, "ROLE_USER"))))
            .build();

    private final static User USER_WITH_NULL_LOGIN = User.builder()
            .id(3)
            .login(null)
            .password("pass")
            .build();

    @ParameterizedTest
    @MethodSource("getArgumentsForMappingToUserDtoTest")
    @Tag("toUserDto")
    @DisplayName("All fields should be equal, when user is mapped to UserDto")
    void AllFieldsAreEqual_whenUserIsMappedToUserDto(User user) {
        var userDto = userMapper.toUserDto(user);
        assertThat(userDto)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", user.getId())
                .hasFieldOrPropertyWithValue("password", user.getPassword())
                .hasFieldOrPropertyWithValue("login", user.getLogin())
                .hasFieldOrPropertyWithValue("workExp", user.getWorkExp())
                .hasFieldOrPropertyWithValue("roles", user.getRoles())
                .hasFieldOrPropertyWithValue("birthDate", user.getBirthDate())
                .hasFieldOrPropertyWithValue("lastName", user.getLastName())
                .hasFieldOrPropertyWithValue("firstName", user.getFirstName());
    }

    static Stream<Arguments> getArgumentsForMappingToUserDtoTest() {
        return Stream.of(
                Arguments.of(USER_WITH_ALL_FIELDS),
                Arguments.of(USER_WITHOUT_ROLES)
        );
    }

}
