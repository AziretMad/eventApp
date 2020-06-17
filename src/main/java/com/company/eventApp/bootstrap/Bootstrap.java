//package com.company.eventApp.bootstrap;
//
//import com.company.eventApp.dto.PlaceTagDTO;
//import com.company.eventApp.entity.*;
//import com.company.eventApp.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class Bootstrap implements CommandLineRunner {
//    @Autowired
//    PlaceTagRepo placeTagRepo;
//
//    @Autowired
//    UserRepo userRepo;
//
//    @Autowired
//    PlaceRepo placeRepo;
//
//    @Autowired
//    TagRepo tagRepo;
//
//    @Autowired
//    EventRepo eventRepo;
//    @Override
//    public void run(String... args) throws Exception {
//        User user = User.builder()
//                .login("LuckyChosenOne")
//                .password("idk123")
//                .telephone("0666321765")
//                .email("madreazer@gmail.com")
//                .isActive(1)
//                .build();
//        userRepo.save(user);
//        PlaceTag placeTag = PlaceTag.builder()
//                .name("bar").build();
//        PlaceTag placeTag1 = PlaceTag.builder()
//                .name("cafe").build();
//        List<PlaceTag> placeTags = new ArrayList<>();
//        placeTags.add(placeTag);
//        placeTags.add(placeTag1);
//        placeTagRepo.save(placeTag);
//        placeTagRepo.save(placeTag);
//        Place place = Place.builder()
//                .address("avenue 12 / Alien street")
//                .rentalPrice(BigDecimal.valueOf(10000))
//                .name("Night Witch")
//                .user(user)
//                .placeTags(placeTags)
//                .build();
//        placeRepo.save(place);
//        Date date = new Date();
//        Tag tag = Tag.builder()
//                .name("comedy").build();
//        Tag tag1 = Tag.builder()
//                .name("night life").build();
//        tagRepo.save(tag);
//        tagRepo.save(tag1);
//        List<Tag> tags = new ArrayList<>();
//        tags.add(tag);
//        tags.add(tag1);
//        Event event = Event.builder()
//                .name("Stand Up")
//                .picture("asd/dsa/asd")
//                .date(date)
//                .user(user)
//                .description("You will laugh to the death")
//                .place(place)
//                .tags(tags)
//                .build();
//        eventRepo.save(event);
//    }
//}
