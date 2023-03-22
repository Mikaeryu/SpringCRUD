package app.dao;

import app.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public User saveUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public User findUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUser(String login) {
        String jpqlQuery = "SELECT u FROM User u WHERE u.login = :login";

        return entityManager.createQuery(jpqlQuery, User.class).setParameter("login", login).getSingleResult();
    }

    @Transactional
    @Override
    public User updateUser(int id, User updatedUser) {
        User userToUpdate = findUser(id);
        updatedUser.setId(userToUpdate.getId());
        entityManager.merge(updatedUser);
        return updatedUser;
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        User user = findUser(id);
        if (!entityManager.contains(user)) {
            user = entityManager.merge(user);
        }
        entityManager.remove(user);
    }

    @Override
    public List<User> getUserList() {
        String jpqlQuery = "SELECT u FROM User u";

        return entityManager.createQuery(jpqlQuery, User.class).getResultList();
    }
}
