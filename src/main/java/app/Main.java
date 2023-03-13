package app;

import app.dao.UserDaoImpl;
import app.model.User;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        User user = new User();
        user.setAge(22);
        user.setBirthDate(LocalDate.of(2022, 9, 23));
        user.setFirstName("Ivan");
        user.setLastName("Fedorovich");

        userDao.save(user);
        userDao.save(user);
    }
}
