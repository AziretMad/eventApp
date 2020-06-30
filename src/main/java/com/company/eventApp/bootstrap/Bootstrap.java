package com.company.eventApp.bootstrap;

import com.company.eventApp.dto.EventDTO;
import com.company.eventApp.dto.PlaceTagDTO;
import com.company.eventApp.dto.RoleDTO;
import com.company.eventApp.dto.UserDTO;
import com.company.eventApp.entity.*;
import com.company.eventApp.repository.*;
import com.company.eventApp.service.EventServiceImpl;
import com.company.eventApp.service.RoleServiceImpl;
import com.company.eventApp.service.TagServiceImpl;
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
    @Autowired
    PlaceRepo placeRepo;
    @Autowired
    EventServiceImpl eventService;
    @Autowired
    EventRepo eventRepo;
    @Autowired
    TagServiceImpl tagService;

    public void run(String... args) throws Exception {

        userRepo.deleteAllInBatch();
        roleRepo.deleteAllInBatch();

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
        Tag tag2 = Tag.builder()
                .name("music").build();
        Tag tag3 = Tag.builder()
                .name("punk rock").build();
        Tag tag4 = Tag.builder()
                .name("grunge").build();
        Tag tag5 = Tag.builder()
                .name("alternative hip hop").build();
        Tag tag6 = Tag.builder().name("hip hop").build();
        Tag tag7 = Tag.builder().name("rap rock").build();
        tagRepo.save(tag);
        tagRepo.save(tag1);
        tagRepo.save(tag2);
        tagRepo.save(tag3);
        tagRepo.save(tag4);
        tagRepo.save(tag5);
        tagRepo.save(tag6);
        tagRepo.save(tag7);
        PlaceTag placeTag = PlaceTag.builder()
                .name("sports ground")
                .build();
        PlaceTag placeTag1 = PlaceTag.builder()
                .name("basketball playground").build();
        Place place = Place.builder()
                .name("Jordan basketball playground").build();
        PlaceTag placeTag2 = PlaceTag.builder()
                .name("concert hall")
                .build();
        PlaceTag placeTag3 = PlaceTag.builder()
                .name("bar").build();
        Place place1 = Place.builder()
                .name("night witch")
                .address("garden avenue 321").build();
        Set<PlaceTag> placeTags1 = new HashSet<>();
        placeTags1.add(placeTag2);
        placeTags1.add(placeTag3);
        place1.setPlaceTags(placeTags1);
        Set<Place> placeSet = new HashSet<>();
        placeSet.add(place1);
        placeTag2.setPlaces(placeSet);
        placeTag3.setPlaces(placeSet);
        placeRepo.save(place1);
        Set<PlaceTag> placeTags = new HashSet<>();
        placeTags.add(placeTag);
        placeTags.add(placeTag1);
        place.setPlaceTags(placeTags);
        Set<Place> places = new HashSet<>();
        places.add(place);
        placeTag.setPlaces(places);
        placeTag1.setPlaces(places);
        placeRepo.save(place);
        Set<String> tagNames = new HashSet<>();
        tagNames.add("sport");
        tagNames.add("sport");

//        EventDTO eventDTO = EventDTO.builder()
//                .date(new Date())
//                .description("fuck you")
//                .picture("no pic")
//                .video("no vid")
//                .name("fiba 3x3")
//                .tags(tagNames).build();
//        eventService.create(eventDTO);
//        UserDTO userDTO = UserDTO.builder()
//                .login("user")
//                .password("password")
//                .email("user@gmail.com")
//                .telephone("0111222333")
//                .build();
//        userService.create(userDTO);

    }
}
