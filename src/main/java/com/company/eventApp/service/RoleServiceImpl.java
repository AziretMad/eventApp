package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.entity.Role;
import com.company.eventApp.dto.RoleDTO;
import com.company.eventApp.repository.RoleRepo;
import com.company.eventApp.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Role create(RoleDTO entity) throws Exception {
        Role role = Role.builder()
                .roleName(entity.getRoleName())
                .user(userService.getById(entity.getUserId()))
                .build();
        return roleRepo.save(role);
    }

    @Override
    public Role getById(Long id) {
        Optional<Role> role = roleRepo.findById(id);
        return role.get();
    }

    @Override
    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        roleRepo.deleteById(id);
    }
}
