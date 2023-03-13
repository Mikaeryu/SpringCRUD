package app;

import app.config.AppConfig;
import app.dao.UserDaoImpl;
import app.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDaoImpl userDao = context.getBean(UserDaoImpl.class);

        User user = new User();
        user.setAge(22);
        user.setBirthDate(LocalDate.of(2022, 9, 23));
        user.setFirstName("Ivan");
        user.setLastName("Fedorovich");

        userDao.save(user);
        userDao.save(user);

        context.close();
    }
}
