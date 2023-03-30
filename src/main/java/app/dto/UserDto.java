package app.dto;

import app.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {
    private long id;

    private String firstName;

    private String lastName;

    private int workExp;

    private LocalDate birthDate;

    private String login;

    private String password;

    private Set<Role> roles = new HashSet<>();
}
