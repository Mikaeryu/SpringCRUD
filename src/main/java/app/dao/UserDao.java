package app.dao;

import app.model.User;
import java.util.List;

public interface UserDao {
    User saveUser(User user);

    User findUser(long id);

    User findUserByLogin(String login);

    User updateUser(int id, User updatedUser);

    void deleteUser(long id);

    List<User> getUserList();
}
