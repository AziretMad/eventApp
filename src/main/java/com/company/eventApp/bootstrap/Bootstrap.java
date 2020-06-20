package com.company.eventApp.bootstrap;

import com.company.eventApp.dto.PlaceTagDTO;
import com.company.eventApp.dto.RoleDTO;
import com.company.eventApp.dto.UserDTO;
import com.company.eventApp.entity.*;
import com.company.eventApp.repository.*;
import com.company.eventApp.service.RoleServiceImpl;
import com.company.eventApp.service.UserServiceImpl;
import com.company.eventApp.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    TagRepo tagRepo;
    @Autowired
    PlaceTagRepo placeTagRepo;

    public void run(String... args) throws Exception {

        userRepo.deleteAllInBatch();
        roleRepo.deleteAllInBatch();

        User user = User.builder()
                .login("user")
                .password("password")
                .build();

        Role role = Role.builder()
                .roleName("ADMIN")
                .build();

        Role role1 = Role.builder()
                .roleName("USER").build();

        Role role2 = Role.builder()
                .roleName("OWNER").build();

        roleRepo.save(role);
        roleRepo.save(role1);
        Tag tag = Tag.builder()
                .name("sport")
                .build();
        Tag tag1 = Tag.builder()
                .name("basketball")
                .build();
        tagRepo.save(tag);
        tagRepo.save(tag1);
        PlaceTag placeTag = PlaceTag.builder()
                .name("sports ground")
                .build();
        PlaceTag placeTag1 = PlaceTag.builder()
                .name("basketball playground").build();
        placeTagRepo.save(placeTag);
        placeTagRepo.save(placeTag1);

//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        roles.add(role1);
//
//        user.setRoles(roles);
//
//        Set<User> users = new HashSet<>();
//        users.add(user);
//
//        role.setUsers(users);
//        role1.setUsers(users);
//
//        userRepo.save(user);

    }
}
