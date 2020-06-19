package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.entity.Place;
import com.company.eventApp.entity.PlaceTag;
import com.company.eventApp.dto.PlaceDTO;
import com.company.eventApp.dto.PlaceTagDTO;
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

    @Override
    public Place create(PlaceDTO placeDTO) throws Exception {
        Place place = Place.builder()
                .name(placeDTO.getName())
                .address(placeDTO.getAddress())
                .rentalPrice(placeDTO.getRentalPrice())
                .address(placeDTO.getAddress())
                .build();
        Set<String> placeTagNames = new HashSet<>();
        Set<PlaceTag> placeTags = new HashSet<>();
        for(String s : placeTagNames){
            placeTags.add(placeTagService.getByName(s));
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
    public void delete(Long id) {
        placeRepo.deleteById(id);
    }
}
