package app.dao;

import app.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao{

    private final EntityManager entityManager;

    @Override
    public Role saveRole(Role role) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(role);

        transaction.commit();

        return role;
    }

    @Override
    public Role findRole(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findRoleByName(String  name) {
        String jpqlQuery = "SELECT r FROM Role r WHERE r.name = :name";

        return entityManager.createQuery(jpqlQuery, Role.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public void deleteRole(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Role role = findRole(id);
        if (!entityManager.contains(role)) {
            role = entityManager.merge(role);
        }
        entityManager.remove(role);

        transaction.commit();
    }
}
