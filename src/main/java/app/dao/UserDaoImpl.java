package app.dao;

import app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void saveUser(User user) {
        EntityManager entityManager = createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(user);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public User findUser(long id) {
        EntityManager entityManager = createEntityManager();
        User foundUser = entityManager.find(User.class, id);
        entityManager.close();

        return foundUser;
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        User userToUpdate = findUser(id);
        updatedUser.setId(userToUpdate.getId());

        EntityManager entityManager = createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(updatedUser);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public void deleteUser(long id) {
        EntityManager entityManager = createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user = findUser(id);
        user = entityManager.merge(user);
        entityManager.remove(user);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public List<User> getUserList() {
        EntityManager entityManager = createEntityManager();
        String jpqlQuery = "SELECT u FROM User u";
        List<User> userList = entityManager.createQuery(jpqlQuery, User.class).getResultList();
        entityManager.close();

        return userList;
    }
}
