package app.dao;

import app.model.User;
import java.util.List;

public interface UserDao {
    void saveUser(User user);

    User getUser(long id);

    void updateUser(int id, User updatedUser);

    void deleteUser(long id);

    List<User> getUserList();
}
