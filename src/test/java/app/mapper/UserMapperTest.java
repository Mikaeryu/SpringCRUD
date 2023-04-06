package app.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import app.model.Role;
import app.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

class UserMapperTest {
    private UserMapper userMapper;

    @BeforeEach
    void initializeUserMapper() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForMappingTest")
    @DisplayName("All fields should be equal, when calling toUserDto() method")
    void AllFieldsShouldBeEqual_whenUserIsMappedToUserDto(User userArg) {
        var userDto = userMapper.toUserDto(userArg);

        assertThat(userDto)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", userArg.getId())
                .hasFieldOrPropertyWithValue("password", userArg.getPassword())
                .hasFieldOrPropertyWithValue("login", userArg.getLogin())
                .hasFieldOrPropertyWithValue("workExp", userArg.getWorkExp())
                .hasFieldOrPropertyWithValue("roles", userArg.getRoles())
                .hasFieldOrPropertyWithValue("birthDate", userArg.getBirthDate())
                .hasFieldOrPropertyWithValue("lastName", userArg.getLastName())
                .hasFieldOrPropertyWithValue("firstName", userArg.getFirstName());
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForMappingTest")
    @DisplayName("All fields should be equal, when calling toUser() method")
    void AllFieldsShouldBeEqual_whenUserDtoIsMappedToUser(User userArg) {
        var userDto = userMapper.toUserDto(userArg); //тут просто перемапил обратно из аргументов, т.к. первый тест уже проверяет, что метод рабочий

        var user = userMapper.toUser(userDto);

        assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", userDto.getId())
                .hasFieldOrPropertyWithValue("password", userDto.getPassword())
                .hasFieldOrPropertyWithValue("login", userDto.getLogin())
                .hasFieldOrPropertyWithValue("workExp", userDto.getWorkExp())
                .hasFieldOrPropertyWithValue("roles", userDto.getRoles())
                .hasFieldOrPropertyWithValue("birthDate", userDto.getBirthDate())
                .hasFieldOrPropertyWithValue("lastName", userDto.getLastName())
                .hasFieldOrPropertyWithValue("firstName", userDto.getFirstName());
    }

    @Test
    void updateUserFromDto() {

    }

    static Stream<Arguments> getArgumentsForMappingTest() {
        final User USER_WITHOUT_ROLES = User.builder()
                .id(1)
                .login("user1")
                .password("pass1")
                .firstName("User1")
                .lastName("Userovich1")
                .workExp(3)
                .birthDate(LocalDate.EPOCH)
                .build();

        final User CORRECT_USER = User.builder()
                .id(2)
                .login("user2")
                .password("pass2")
                .firstName("User2")
                .lastName("Userovich2")
                .workExp(3)
                .birthDate(LocalDate.MAX)
                .roles(new HashSet<>(List.of(new Role(1L, "ROLE_USER"))))
                .build();

        final User USER_WITH_DEFAULT_FIELDS = User.builder().build();

        return Stream.of(
                Arguments.of(CORRECT_USER),
                Arguments.of(USER_WITHOUT_ROLES),
                Arguments.of(USER_WITH_DEFAULT_FIELDS)
        );
    }

}
