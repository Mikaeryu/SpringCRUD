package app.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import app.dto.UserDto;
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
import java.util.Set;
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
    void AllFieldsShouldBeEqual_whenUserIsMappedToDto(User userArg) {
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
    void AllFieldsShouldBeEqual_whenDtoIsMappedToUser(User userArg) {
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
    @DisplayName("updateUserFromDto() method casual case")
    void updateUserFromDto() {
        var userBeforeUpdate = getUser();
        var userClone = getUser();

        var userDto = UserDto.builder()
                .id(2L)
                .password("updated pass")
                .workExp(5)
                .birthDate(LocalDate.MIN)
                .build();


        System.out.println(userBeforeUpdate);
        System.out.println(userClone);
        userMapper.updateUserFromDto(userDto, userClone);
        System.out.println(userBeforeUpdate);
        System.out.println(userClone);

        assertThat(userClone)
                .hasFieldOrPropertyWithValue("password", userDto.getPassword())
                .hasFieldOrPropertyWithValue("workExp", userDto.getWorkExp())
                .hasFieldOrPropertyWithValue("birthDate", userDto.getBirthDate())
                //field below should not change
                .hasFieldOrPropertyWithValue("id", userBeforeUpdate.getId())
                .hasFieldOrPropertyWithValue("login", userBeforeUpdate.getLogin())
                .hasFieldOrPropertyWithValue("firstName", userBeforeUpdate.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", userBeforeUpdate.getLastName())
                .hasFieldOrPropertyWithValue("roles", userBeforeUpdate.getRoles());
    }

    @Test
    @DisplayName("updateUserFromDto() case with roleSet update")
    void UserRolesShouldCorrectlyUpdate_whenUpdatingUserFromDto() {
        var userBeforeUpdate = getUser();
        var userClone = getUser();

        Set<Role> rolesSet = new HashSet<>(List.of(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN")));
        var userDto = UserDto.builder()
                .roles(rolesSet)
                .build();

        userMapper.updateUserFromDto(userDto, userClone);

        assertThat(userClone)
                .hasFieldOrPropertyWithValue("roles", userDto.getRoles())
                //fields below should not change
                .hasFieldOrPropertyWithValue("password", userBeforeUpdate.getPassword())
                .hasFieldOrPropertyWithValue("workExp", userBeforeUpdate.getWorkExp())
                .hasFieldOrPropertyWithValue("birthDate", userBeforeUpdate.getBirthDate())
                .hasFieldOrPropertyWithValue("id", userBeforeUpdate.getId())
                .hasFieldOrPropertyWithValue("login", userBeforeUpdate.getLogin())
                .hasFieldOrPropertyWithValue("firstName", userBeforeUpdate.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", userBeforeUpdate.getLastName());
    }

    private User getUser() {
        return User.builder()
                .id(2)
                .login("user")
                .password("pass")
                .firstName("User")
                .lastName("Userovich")
                .workExp(3)
                .birthDate(LocalDate.of(1994, 12, 23))
                .roles(new HashSet<>(List.of(new Role(1L, "ROLE_USER"))))
                .build();
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

        final User CASUAL_USER = User.builder()
                .id(2)
                .login("user2")
                .password("pass2")
                .firstName("User2")
                .lastName("Userovich2")
                .workExp(3)
                .birthDate(LocalDate.MAX)
                .roles(new HashSet<>(List.of(new Role(1L, "ROLE_USER"))))
                .build();

        final User USER_WITH_DEFAULT_VALUES = User.builder().build();

        return Stream.of(
                Arguments.of(CASUAL_USER),
                Arguments.of(USER_WITHOUT_ROLES),
                Arguments.of(USER_WITH_DEFAULT_VALUES)
        );
    }
}
