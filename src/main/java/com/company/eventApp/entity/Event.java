package com.company.eventApp.entity;

import com.company.eventApp.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "event")
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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "event_tags",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    Set<Tag> tags = new HashSet<>();

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
    @JoinColumn(name = "e_user_id", referencedColumnName = "id")
    User user;
}
