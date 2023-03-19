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
import java.util.Arrays;
import java.util.HashSet;

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

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleDao.findRoleByName("ROLE_ADMIN");
        Role userRole = roleDao.findRoleByName("ROLE_USER");
        User user = new User();
        user.setFirstName("ADMIN");
        user.setLastName("ADMIN");
        user.setPassword("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
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
