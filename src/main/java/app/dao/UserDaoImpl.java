package app.dao;

import app.model.Role;
import app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{

    private final EntityManager entityManager;

    @Override
    public User saveUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(user);

        transaction.commit();

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

    @Override
    public User updateUser(int id, User updatedUser) {
        User userToUpdate = findUser(id);
        updatedUser.setId(userToUpdate.getId());

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(updatedUser);
        transaction.commit();

        return updatedUser;
    }

    @Override
    public void deleteUser(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user = findUser(id);
        if (!entityManager.contains(user)) {
            user = entityManager.merge(user);
        }
        entityManager.remove(user);

        transaction.commit();
    }

    @Override
    public List<User> getUserList() {
        String jpqlQuery = "SELECT u FROM User u";

        return entityManager.createQuery(jpqlQuery, User.class).getResultList();
    }
}
