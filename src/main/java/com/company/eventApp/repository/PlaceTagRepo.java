package com.company.eventApp.repository;

import com.company.eventApp.entity.PlaceTag;
import com.company.eventApp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceTagRepo extends JpaRepository<PlaceTag, Long> {
    @Query("select placeTag from PlaceTag placeTag where placeTag.name = :name")
    PlaceTag getByName(@Param("name") String name);
}
