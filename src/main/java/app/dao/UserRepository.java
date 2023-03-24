package app.dao;

import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User findUserBy(long id);

    User findUserBy(String login);

    User updateUser(User user);

    void deleteUserBy(long id);

    List<User> getAll();
}
