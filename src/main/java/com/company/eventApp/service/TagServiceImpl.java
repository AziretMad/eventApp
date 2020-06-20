package com.company.eventApp.service;//package com.company.eventApp.service;

import com.company.eventApp.entity.Tag;
import com.company.eventApp.dto.TagDTO;
import com.company.eventApp.repository.TagRepo;
import com.company.eventApp.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepo tagRepo;

    @Override
    public Tag create(TagDTO entity) throws Exception {
        Tag tag = Tag.builder()
                .name(entity.getName())
                .build();
        return tagRepo.save(tag);
    }

    @Override
    public Tag getById(Long id) {
        Optional<Tag> tag = tagRepo.findById(id);
        return tag.get();
    }

    @Override
    public List<Tag> getAll() {
        return tagRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        tagRepo.deleteById(id);
    }

    @Override
    public Tag update(Tag entity) {
        return tagRepo.save(entity);
    }

    public Tag getByName(String name){
        return tagRepo.getByName(name);
    }
}
