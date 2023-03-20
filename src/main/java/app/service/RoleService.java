package app.service;

import app.model.Role;

public interface RoleService {
    Role saveRole(Role role);

    Role findRole(long id);

    Role findRole(String name);

    void deleteRole(long id);
}
