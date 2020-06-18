package com.company.eventApp.bootstrap;

import com.company.eventApp.dto.PlaceTagDTO;
import com.company.eventApp.dto.RoleDTO;
import com.company.eventApp.entity.*;
import com.company.eventApp.repository.*;
import com.company.eventApp.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    PlaceTagRepo placeTagRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PlaceRepo placeRepo;

    @Autowired
    TagRepo tagRepo;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    RoleRepo roleRepo;
    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .login("user")
                .password("password")
                .telephone("0666321765")
                .email("madreazer@gmail.com")
                .isActive(1)
                .build();
        userRepo.save(user);
        Role role = Role.builder()
                .user(user)
                .roleName("ROLE_ADMIN")
                .build();
        roleRepo.save(role);
    }
}
