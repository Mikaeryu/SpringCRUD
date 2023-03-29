package app.service.impl;

import app.dao.RoleRepository;
import app.model.Role;
import app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRole(long id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public Role findRole(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }
}
