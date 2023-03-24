package app.service;

import app.dao.UserDao;
import app.dao.UserRepository;
import app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUser(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUser(String  login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User updateUser(int id, User updatedUser) { // НЕ РЕАЛИЗОВАНО ЧЕРЕЗ userRepository
        return userDao.updateUser(id, updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.getAllUsers();
    }
}
