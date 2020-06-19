package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.entity.Place;
import com.company.eventApp.entity.PlaceTag;
import com.company.eventApp.dto.PlaceDTO;
import com.company.eventApp.dto.PlaceTagDTO;
import com.company.eventApp.repository.PlaceRepo;
import com.company.eventApp.service.interfaces.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepo placeRepo;

    @Autowired
    private PlaceTagServiceImpl placeTagService;

    @Override
    public Place create(PlaceDTO entity) throws Exception {
        Place place = Place.builder()
                .name(entity.getName())
                .address(entity.getAddress())
                .rentalPrice(entity.getRentalPrice())
                .address(entity.getAddress())
                .build();
        List<PlaceTagDTO> placeTagDTOS = new ArrayList<>();
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
