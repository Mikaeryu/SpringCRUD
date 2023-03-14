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
    //ВОЗМОЖНО стоит убрать эту простыню из сеттеров и геттеров и перенести в конфиг?
    //типаа, вызывать просто метод getEntityManager из утилитного класса
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

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public List<User> getUserList() {
        return null;
    }
}
