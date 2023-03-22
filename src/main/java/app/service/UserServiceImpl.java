package app.service;

import app.dao.UserDao;
import app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Override
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User findUser(long id) {
        return userDao.findUser(id);
    }

    @Override
    public User findUser(String  login) {
        return userDao.findUser(login);
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
