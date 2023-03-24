package app.dao;

import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    User save(User user);

    User findUserById(long id);

    User findUserByLogin(String login);

    @Transactional
    void deleteUserById(long id);

    @Query("SELECT u FROM User u")
    List<User> getAllUsers();
}
