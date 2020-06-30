package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.dto.TagDTO;
import com.company.eventApp.entity.Event;
import com.company.eventApp.entity.Place;
import com.company.eventApp.entity.Tag;
import com.company.eventApp.enums.Status;
import com.company.eventApp.dto.EventDTO;
import com.company.eventApp.model.Cancel;
import com.company.eventApp.model.MovedDate;
import com.company.eventApp.repository.EventRepo;
import com.company.eventApp.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                .description(eventDTO.getDescription())
                .status(Status.PLANNED)
                .name(eventDTO.getName())
                .place(place)
                .user(userService.getById(eventDTO.getUserId()))
                .build();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = simpleDateFormat.parse(eventDTO.getDate());
            event.setDate(date);
        }
        catch (ParseException e){
            System.out.println(e.getErrorOffset());
        }
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

    public Event addImage(Long id, MultipartFile picture) throws IOException {
        Event event = getById(id);
        String pict = getModifiedName(picture);
        event.setPicture(pict);
        return eventRepo.save(event);
    }

    private String getModifiedName(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String modifiedFileName = System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
        Path path = Paths.get("C:\\Users\\admin-pc\\Desktop\\Site\\" + modifiedFileName);
        Files.write(path, bytes);
        return modifiedFileName;
    }

    public Event moveDate(MovedDate movedDate){
        if(movedDate.getReason() == null){
            movedDate.setReason("НЕ УКАЗАНО");
        }
        Event event = getById(movedDate.getEventId());
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = simpleDateFormat.parse(movedDate.getDate());
            event.setDate(date);
        }
        catch (ParseException e){
            System.out.println(e.getErrorOffset());
        }
        event.setDescription(event.getDescription()
        + " ПЕРЕНЕСОНО "
        + "ПРИЧИНА: " + movedDate.getReason());
        event.setStatus(Status.MOVED);
        return eventRepo.save(event);
    }

    public Event cancel(Cancel cancel){
        if(cancel.getReason() == null){
            cancel.setReason("НЕ УКАЗАНО");
        }
        Event event = getById(cancel.getEventId());
        event.setStatus(Status.CANCELLED);
        event.setDescription(event.getDescription()
                + " ОТМЕНЕНО "
                + "ПРИЧИНА: " + cancel.getReason());
        return eventRepo.save(event);
    }

    public List<Event> getSoonEvents(){
        return eventRepo.getSoonEvents();
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
