package app.dao;

import app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    //@Autowired
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-mvc"); //тут лучше бы связать бином

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(User user) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.merge(user);
        transaction.commit();

        entityManager.close();
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public void update(int id, User updatedUser) {

    }

    @Override
    public void deleteUserById(long id) {

    }

    @Override
    public List<User> getList() {
        return null;
    }
}
