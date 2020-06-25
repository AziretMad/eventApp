package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.dto.TagDTO;
import com.company.eventApp.entity.Event;
import com.company.eventApp.entity.Place;
import com.company.eventApp.entity.Tag;
import com.company.eventApp.enums.Status;
import com.company.eventApp.dto.EventDTO;
import com.company.eventApp.repository.EventRepo;
import com.company.eventApp.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    PlaceServiceImpl placeService;

    @Override
    public Event create(EventDTO eventDTO) throws Exception {
        Place place = placeService.getByName(eventDTO.getPlace());
        Event event = Event.builder()
                .date(eventDTO.getDate())
                .description(eventDTO.getDescription())
                .picture(eventDTO.getPicture())
                .video(eventDTO.getVideo())
                .status(Status.PLANNED)
                .name(eventDTO.getName())
                .user(userService.getById(eventDTO.getUserId()))
                .build();
        Set<TagDTO> tagDTOs = eventDTO.getTagDTOs();
        Set<Tag> tags = new HashSet<>();
        for (TagDTO tagDTO : tagDTOs){
            tags.add(tagService.getByName(tagDTO.getName()));
        }
        event.setTags(tags);
        Set<Event> events = new HashSet<>();
        events.add(event);
        for(Tag tag : tags){
            if(tag.getEvents() == null){
                tag.setEvents(events);
            }
            else {
                tag.getEvents().add(event);
            }
        }
        return eventRepo.save(event);
    }

    public List<Event> getSoonEvents(){
        Date date = new Date();
        return eventRepo.getSoonEvents(date);
    }

    @Override
    public Event getById(Long id) {
        Optional<Event> event = eventRepo.findById(id);
        return event.get();
    }

    @Override
    public List<Event> getAll() {
        return eventRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        eventRepo.deleteById(id);
    }

    @Override
    public Event update(Event entity) {
        return eventRepo.save(entity);
    }
}
