package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.dto.PlaceTagDTO;
import com.company.eventApp.entity.PlaceTag;
import com.company.eventApp.repository.PlaceTagRepo;
import com.company.eventApp.service.interfaces.PlaceTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceTagServiceImpl implements PlaceTagService {
    @Autowired
    private PlaceTagRepo placeTagRepo;

    @Override
    public PlaceTag create(PlaceTagDTO entity) throws Exception {
        PlaceTag placeTag = PlaceTag.builder()
                .name(entity.getName())
                .build();
        return placeTagRepo.save(placeTag);
    }

    @Override
    public PlaceTag getById(Long id) {
        Optional<PlaceTag> placeTag = placeTagRepo.findById(id);
        return placeTag.get();
    }

    @Override
    public List<PlaceTag> getAll() {
        return placeTagRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        placeTagRepo.deleteById(id);
    }

    @Override
    public PlaceTag update(PlaceTag entity) {
        return placeTagRepo.save(entity);
    }

    public PlaceTag getByName(String name){
        return placeTagRepo.getByName(name);
    }
}
