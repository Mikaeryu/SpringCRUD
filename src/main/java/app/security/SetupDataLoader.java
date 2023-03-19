package app.security;

import app.dao.RoleDao;
import app.dao.UserDao;
import app.model.Role;
import app.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserDao userDao;

    private final RoleDao roleDao;

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
        if(userDao.getUserList().isEmpty()) userDao.saveUser(user);
        alreadySetup = true;
    }
}
