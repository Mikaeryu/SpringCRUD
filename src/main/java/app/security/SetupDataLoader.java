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

    boolean alreadySetup = true;

    private final UserDao userDao;

    private final RoleDao roleDao;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) return;

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleDao.findRoleByName("ROLE_ADMIN");
        User user = new User();
        user.setId(0);
        user.setLogin("ADMIN");
        user.setPassword("ADMIN");
        user.getRoles().add(adminRole);
        userDao.saveUser(user);

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleDao.findRoleByName(name);
        if (role == null) {
            role = new Role(name);
            roleDao.saveRole(role);
        }
        return role;
    }
}
