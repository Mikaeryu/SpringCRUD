package app.dao;

import app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Transactional
    Role save(Role role);

    Role findRoleById(long id);

    Role findRoleByName(String name);

    @Transactional
    void deleteRoleById(long id);
}
