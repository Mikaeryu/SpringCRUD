package app.dao;

import app.model.User;
import java.util.List;

public interface UserDao {
    void save(User user);

    User getById(long id);

    void update(int id, User updatedUser);

    void deleteUserById(long id);

    List<User> getList();
}
