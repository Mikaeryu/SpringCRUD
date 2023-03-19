package app.security;

import app.model.Role;
import app.model.User;
import app.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserService userService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        Role adminRole = new Role("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("ADMIN");
        user.setLogin("admin");
        user.setPassword("admin");
        user.getRoles().add(adminRole);

        List<User> users = userService.getUserList();
        if(users.isEmpty() || users.stream().map(User::getRoles).anyMatch(roles -> roles == adminRole)) { // ТУТ ДОПИЛИ
            userService.saveUser(user);
        }
        alreadySetup = true;
    }
}
