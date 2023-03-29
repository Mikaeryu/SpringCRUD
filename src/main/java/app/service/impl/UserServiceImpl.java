package app.service.impl;

import app.dao.UserRepository;
import app.model.User;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
