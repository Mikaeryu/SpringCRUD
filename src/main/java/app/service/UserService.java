package app.service;

import app.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);

    User findUser(long id);

    User updateUser(int id, User updatedUser);

    void deleteUser(long id);

    List<User> getUserList();
}
