package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.dto.TagDTO;
import com.company.eventApp.entity.Event;
import com.company.eventApp.entity.Tag;
import com.company.eventApp.enums.Status;
import com.company.eventApp.dto.EventDTO;
import com.company.eventApp.repository.EventRepo;
import com.company.eventApp.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Event create(EventDTO entity) throws Exception {
        Event event = Event.builder()
                .date(entity.getDate())
                .description(entity.getDescription())
                .picture(entity.getPicture())
                .video(entity.getVideo())
                .status(Status.PLANNED)
                .name(entity.getName())
                .user(userService.getById(entity.getUserId()))
                .build();
        List<TagDTO> tagDTOs = entity.getTagDTOS();
        List<Tag> tags = new ArrayList<>();
        for(TagDTO tagDTO : tagDTOs){
            tags.add(tagService.getByName(tagDTO.getName()));
        }
        event.setTags(tags);
        return eventRepo.save(event);
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
}
