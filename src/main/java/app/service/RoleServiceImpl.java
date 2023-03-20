package app.service;

import app.dao.RoleDao;
import app.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
        return roleDao.findRole(name);
    }

    @Override
    public void deleteRole(long id) {
        roleDao.deleteRole(id);
    }
}
