package app.dao;

import app.model.Role;

public interface RoleDao {
    Role saveRole(Role role);

    Role findRole(long id);

    void deleteRole(long id);
}