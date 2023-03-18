package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.
@Getter
@Setter
@AllArgsConstructor
public class Role implements GrantedAuthority {
    private Long id;
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
