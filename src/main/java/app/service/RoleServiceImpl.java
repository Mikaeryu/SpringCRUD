package app.service;

import app.dao.RoleDao;
import app.model.Role;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    @Override
    public Role saveRole(Role role) {
        return roleDao.saveRole(role);
    }

    @Override
    public Role findRole(long id) {
        return roleDao.findRole(id);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

    @Override
    public void deleteRole(long id) {
        roleDao.deleteRole(id);
    }
}