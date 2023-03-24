package app.service;

import app.model.User;
import java.util.List;

public interface UserService {
    User saveOrUpdateUser(User user);

    User findUser(long id);

    User findUser(String  login);

    void deleteUser(long id);

    List<User> getUserList();
}
