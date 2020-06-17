package com.company.eventApp.entity;

import com.company.eventApp.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "e_event")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    String name;

    @ManyToMany
    @JoinColumn(name = "tags")
    List<Tag> tags;

    @Column(name = "description")
    String description;

    @Column(name = "picture")
    String picture;

    @Column(name = "video")
    String video;

    @Column(name = "date")
    Date date;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "id")
    Place place;

    @Enumerated
    @Column(name = "status")
    Status status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    User user;
}
