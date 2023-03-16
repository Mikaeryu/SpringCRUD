package app.service;

import app.dao.UserDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User findUser(long id) {
        return userDao.findUser(id);
    }

    @Override
    public User updateUser(int id, User updatedUser) {
        return userDao.updateUser(id, updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }
}
