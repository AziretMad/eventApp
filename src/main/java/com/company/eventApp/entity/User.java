package com.company.eventApp.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "e_user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @Email(message = "please provide valid email")
    @Column(name = "email")
    String email;

    @Column(name = "telephone")
    String telephone;

    @Column(name = "is_active")
    Integer isActive;
}
