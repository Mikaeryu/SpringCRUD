package app;

import app.config.AppConfig;
import app.dao.UserDaoImpl;
import app.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDaoImpl userDao = context.getBean(UserDaoImpl.class);

        User user = new User();
        user.setAge(22);
        user.setBirthDate(LocalDate.of(2022, 9, 23));
        user.setFirstName("Ivan");
        user.setLastName("Fedorovich");

        List<User> userList = userDao.getUserList();
        userList.forEach(System.out::println);
        userDao.deleteUser(2);
        userList.forEach(System.out::println);

        context.close();
    }
}
