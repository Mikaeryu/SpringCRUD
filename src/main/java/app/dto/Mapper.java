package app.dto;

import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Set;

@Component
public class Mapper {
    public UserDto toUserDto(User user) {
        Long id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        int workExp = user.getWorkExp();
        LocalDate birthDate = user.getBirthDate();
        String login = user.getLogin();
        String password = user.getPassword();
        Set<Role> roles = user.getRoles();

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setWorkExp(workExp);
        userDto.setBirthDate(birthDate);
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setRoles(roles);

        return userDto;
    }
}
