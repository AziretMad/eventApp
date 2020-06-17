package com.company.eventApp.repository;

import com.company.eventApp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    @Query("select tag from Tag tag where tag.name = :name")
    Tag getByName(@Param("name") String name);
}
