package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.entity.Place;
import com.company.eventApp.entity.PlaceTag;
import com.company.eventApp.dto.PlaceDTO;
import com.company.eventApp.dto.PlaceTagDTO;
import com.company.eventApp.entity.Role;
import com.company.eventApp.entity.User;
import com.company.eventApp.repository.PlaceRepo;
import com.company.eventApp.service.interfaces.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepo placeRepo;

    @Autowired
    private PlaceTagServiceImpl placeTagService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Place create(PlaceDTO placeDTO) throws Exception {
        User user = userService.getById(placeDTO.getUserId());
        Place place = Place.builder()
                .name(placeDTO.getName())
                .address(placeDTO.getAddress())
                .rentalPrice(placeDTO.getRentalPrice())
                .address(placeDTO.getAddress())
                .user(user)
                .build();
        Set<PlaceTagDTO> placeTagDTOs = placeDTO.getPlaceTags();
        Set<PlaceTag> placeTags = new HashSet<>();
        for(PlaceTagDTO placeTagDTO : placeTagDTOs){
            placeTags.add(placeTagService.getByName(placeTagDTO.getName()));
        }
        place.setPlaceTags(placeTags);
        Set<Place> places = new HashSet<>();
        places.add(place);
        for (PlaceTag placeTag : placeTags){
            if(placeTag.getPlaces() == null){
                placeTag.getPlaces().add(place);
            }
            else{
                placeTag.setPlaces(places);
            }
        }
        Role role = Role.builder()
                .roleName("OWNER")
                .build();
        user.getRoles().add(role);
        userService.update(user);
        return placeRepo.save(place);
    }

    @Override
    public Place getById(Long id) {
        Optional<Place> place = placeRepo.findById(id);
        return place.get();
    }

    @Override
    public List<Place> getAll() {
        return placeRepo.findAll();
    }

    @Override
    public Place update(Place entity) {
        return placeRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        placeRepo.deleteById(id);
    }

    public Place getByName(String name){
        return placeRepo.getByName(name);
    }
}
