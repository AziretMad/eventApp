package com.company.eventApp.repository;

import com.company.eventApp.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
    @Query("select event from Event event where event.date > current_date order by event.date asc ")
    List<Event> getSoonEvents();

    @Query("select event from Event event where event.user.login = :login ")
    List<Event> getAllByUserLogin(@Param("login") String login);
}
