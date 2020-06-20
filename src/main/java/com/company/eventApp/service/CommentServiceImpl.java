package com.company.eventApp.service;

import com.company.eventApp.entity.Comment;
import com.company.eventApp.dto.CommentDTO;
import com.company.eventApp.repository.CommentRepo;
import com.company.eventApp.service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Comment create(CommentDTO entity) throws Exception {
        Comment comment = Comment.builder()
                .text(entity.getText())
                .event(eventService.getById(entity.getEventId()))
                .user(userService.getById(entity.getUserId()))
                .build();
        return commentRepo.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        Optional<Comment> comment = commentRepo.findById(id);
        return comment.get();
    }

    @Override
    public Comment update(Comment entity) {
        return commentRepo.save(entity);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        commentRepo.deleteById(id);
    }
}
