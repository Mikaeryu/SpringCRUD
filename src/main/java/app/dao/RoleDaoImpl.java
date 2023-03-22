package app.dao;

import app.model.Role;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao{

    private final EntityManager entityManager;

    @Transactional
    @Override
    public Role saveRole(Role role) {
        entityManager.merge(role);
        return role;
    }

    @Override
    public Role findRole(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findRole(String  name) {
        String jpqlQuery = "SELECT r FROM Role r WHERE r.name = :name";

        return entityManager.createQuery(jpqlQuery, Role.class).setParameter("name", name).getSingleResult();
    }

    @Transactional
    @Override
    public void deleteRole(long id) {
        Role role = findRole(id);
        if (!entityManager.contains(role)) {
            role = entityManager.merge(role);
        }
        entityManager.remove(role);
    }
}
