package com.company.eventApp.repository;

import com.company.eventApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query("select comment from Comment comment where comment.event.id = :id")
    List<Comment> getCommentByEventId(@Param("id") Long eventId);
}
