package app.security;

import app.model.Role;
import app.model.User;
import app.service.UserService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Класс служит для первоначальной инициализации админа.
 * Если в БД нету юзера с правами админа, то он создаётся на старте сервера.
 * Вместе с ним создаются и роли юзера и админа.
 */
@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false; // тут вообще не уверен насчёт переключателя этого, это норм, или костыльно слишком?

    private final UserService userService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        List<User> users = userService.getUserList();
        boolean usersContainsAdminRole = users.stream()
                .map(User::getRoles)
                .flatMap(Collection::stream)
                .map(Role::getName)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if(!users.isEmpty() && usersContainsAdminRole) return;

        User user = new User();
        user.setFirstName("ADMIN");
        user.setLogin("admin");
        user.setPassword("admin");

        Set<Role> userRoleSet = user.getRoles();
        userRoleSet.add(adminRole);
        userRoleSet.add(userRole);

        userService.saveUser(user);

        alreadySetup = true; //после первоначального сетапа метод будет сразу прерываться
    }
}
