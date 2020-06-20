package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.entity.Rating;
import com.company.eventApp.dto.RatingDTO;
import com.company.eventApp.repository.RatingRepo;
import com.company.eventApp.service.interfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private UserServiceImpl userService;



    @Override
    public Rating create(RatingDTO entity) throws Exception {
        Rating rating = Rating.builder()
                .rating(entity.getRating())
                .event(eventService.getById(entity.getEventId()))
                .user(userService.getById(entity.getUserId()))
                .build();
        return ratingRepo.save(rating);
    }

    @Override
    public Rating getById(Long id) {
        Optional<Rating> rating = ratingRepo.findById(id);
        return rating.get();
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepo.findAll();
    }

    @Override
    public Rating update(Rating entity) {
        return ratingRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        ratingRepo.deleteById(id);
    }
}
