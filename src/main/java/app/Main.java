package app;

import app.config.AppConfig;
import app.config.WebConfig;
import app.dao.UserDaoImpl;
import app.model.User;
import app.service.UserService;
import app.service.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserServiceImpl.class);

        User user = new User();
        user.setAge(22);
        user.setBirthDate(LocalDate.of(2022, 9, 23));
        user.setFirstName("Ivan");
        user.setLastName("Fedorovich");

        List<User> userList = userService.getUserList();
        userList.forEach(System.out::println);

        context.close();
    }
}
