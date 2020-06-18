package com.company.eventApp.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "e_place")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    String name;

    @ManyToMany
    @JoinColumn(name = "place_tag")
    List<PlaceTag> placeTags;

    @Column(name = "address")
    String address;

    @Column(name = "rental_price")
    BigDecimal rentalPrice;

    @ManyToOne
    @JoinColumn(name = "e_user_id", referencedColumnName = "id")
    User user;
}
